package com.example.clonepjtairbb.stay.repository.QueryDSL;

import com.example.clonepjtairbb.stay.dto.ReservationRequest;
import com.example.clonepjtairbb.stay.entity.StayReservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import static com.example.clonepjtairbb.stay.entity.QStay.stay;
import static com.example.clonepjtairbb.stay.entity.QStayReservation.stayReservation;

@RequiredArgsConstructor
@Repository
public class StayReservationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    public Boolean existsOverlappingPreviousReservation(ReservationRequest request) {
        StayReservation reservation = jpaQueryFactory
                .selectFrom(stayReservation)
                .where(
                        checkReservationOkay(
                                request.getCheckinDate().toCalendar(), request.getCheckoutDate().toCalendar()
                        ).not()
                )
                .fetchFirst();
        return reservation == null;
    }

    private BooleanExpression checkinDateAfterRequestCheckout(Calendar checkoutRequest){
        return stayReservation.checkinDate.after(checkoutRequest);
    }
    private BooleanExpression checkoutDateBeforeRequestCheckin(Calendar checkinRequest) {
        return stayReservation.checkoutDate.before(checkinRequest);
    }

    private BooleanExpression checkReservationOkay(Calendar checkinRequest, Calendar checkoutRequest){
        return checkinDateAfterRequestCheckout(checkoutRequest)
                .or(checkoutDateBeforeRequestCheckin(checkinRequest));
    }
}
