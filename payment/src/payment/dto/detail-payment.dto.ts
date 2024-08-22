import { ApiProperty } from "@nestjs/swagger";
import { PaymentStatus } from "@prisma/client";

export class DetailPaymentDto {

    @ApiProperty()
    id: string

    @ApiProperty()
    orderId: string;

    @ApiProperty()
    amount: string;

    @ApiProperty()
    userEmail: string;

    @ApiProperty({
        enum: PaymentStatus,
        type: PaymentStatus,
        description: 'Payment status',
    })
    status: PaymentStatus;

    @ApiProperty()
    createdAt: string;

    @ApiProperty()
    updatedAt: string;
}