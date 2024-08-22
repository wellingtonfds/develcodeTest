import { ApiProperty } from "@nestjs/swagger"
import { IsEmail, IsNumber } from "class-validator"

export class ValidatePaymentRequestDto {

    @ApiProperty()
    @IsNumber()
    public orderId: number

    @ApiProperty()
    @IsNumber()
    public amount: number

    @ApiProperty()
    @IsEmail()
    public userEmail: string
}