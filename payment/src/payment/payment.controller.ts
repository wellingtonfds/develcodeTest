import { Body, Controller, Post } from '@nestjs/common';
import { ApiBadRequestResponse, ApiCreatedResponse, ApiTags } from '@nestjs/swagger';
import { DetailPaymentDto } from './dto/detail-payment.dto';
import { ValidatePaymentRequestDto } from './dto/validate-payment-request.dto';
import { PaymentService } from './payment.service';

@Controller('payment')
@ApiTags('Payment')
export class PaymentController {


    constructor(private readonly paymentService: PaymentService) { }

    @ApiCreatedResponse({
        type: DetailPaymentDto,
        description: 'A new payment has been created.',
    })

    @ApiBadRequestResponse({
        schema: {
            example: {
                "statusCode": 400,
                "message": "Failed to payment order"
            }
        },
        description: 'A new payment has been fail.',
    })
    @Post('validate')
    public async validate(@Body() bodyData: ValidatePaymentRequestDto): Promise<DetailPaymentDto> {
        return this.paymentService.validatePayment(bodyData);

    }
}
