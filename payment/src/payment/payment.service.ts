import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { Payment, PaymentStatus } from '@prisma/client';
import { ValidatePaymentRequestDto } from './dto/validate-payment-request.dto';
import { PaymentRepository } from './repository/payment.repository';

@Injectable()
export class PaymentService {
    private countRequest = 0
    private timeOut = null
    constructor(private readonly paymentRepository: PaymentRepository) {

    }


    public async create(data: ValidatePaymentRequestDto): Promise<Payment> {
        return this.paymentRepository.create(data)

    }

    public async updatePaymentStatus(paymentId: string, status: PaymentStatus): Promise<Payment> {
        return await this.paymentRepository.update(paymentId, { status })
    }

    public async validatePayment(data: ValidatePaymentRequestDto) {
        this.countRequest++
        const paymentLimit = 5
        const payment = await this.create(data)

        // simulate a validation
        const disableSimulation = this.countRequest < paymentLimit
        const min = 1
        const max = 2
        const rand = Math.floor(min + Math.random() * (max - min + 1))
        const objectKeys = Object.keys(PaymentStatus)
        const statusRand = disableSimulation ? PaymentStatus.SUCCESS : PaymentStatus[objectKeys[rand]]
        await new Promise(resolve => setTimeout(resolve, 2000));

        // simulate instabilities
        if (!this.timeOut && this.countRequest > paymentLimit) {
            this.timeOut = true
            new Promise(resolve => setTimeout(() => {
                this.countRequest = 0
                this.timeOut = false
                resolve
            }, 60000));
        }

        const updatePayment = await this.updatePaymentStatus(payment.id, statusRand)
        if (updatePayment.status === 'FAILED') {
            throw new HttpException(
                'Failed to payment order',
                HttpStatus.BAD_REQUEST
            )
        }

        return {
            ...updatePayment,
            orderId: updatePayment.orderId.toString(),
            amount: updatePayment.amount.toString(),
            createdAt: updatePayment.createdAt.toISOString(),
            updatedAt: updatePayment.updatedAt.toISOString(),
        }
    }

}
