package com.frb.application.wishlist.addProduct;

import com.frb.application.UseCase;
import com.frb.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class AddProductUseCase
        extends UseCase<AddProductCommand, Either<Notification, AddProductOutput>> {
}
