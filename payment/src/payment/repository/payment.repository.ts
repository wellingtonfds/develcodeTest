import { OnModuleInit } from "@nestjs/common";
import { Payment, Prisma, PrismaClient } from '@prisma/client';


export class PaymentRepository extends PrismaClient implements OnModuleInit {

    async onModuleInit() {
        await this.$connect();
    }


    public async create(data: Prisma.PaymentCreateInput): Promise<Payment> {
        return await this.payment.create({ data });
    }

    public async update(id: string, data: Prisma.PaymentUpdateInput): Promise<Payment> {
        return await this.payment.update({
            where: { id },
            data
        });
    }
}