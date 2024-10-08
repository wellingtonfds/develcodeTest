import { Module } from '@nestjs/common';
import { PaymentController } from './payment.controller';
import { PaymentService } from './payment.service';
import { PaymentRepository } from './repository/payment.repository';

@Module({
  controllers: [PaymentController],
  providers: [PaymentService, PaymentRepository]
})
export class PaymentModule { }
