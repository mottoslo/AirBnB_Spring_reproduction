
package com.example.clonepjtairbb.stay.controller;

import com.example.clonepjtairbb.common.security.UserDetailsImpl;
import com.example.clonepjtairbb.common.utils.Message;
import com.example.clonepjtairbb.stay.dto.RegisterStayRequest;
<<<<<<< HEAD
import com.example.clonepjtairbb.stay.dto.SearchOptionRequest;
=======
import com.example.clonepjtairbb.stay.dto.ReservationRequest;
>>>>>>> c24a81511fa96bc063b7af75430377178f86af8d
import com.example.clonepjtairbb.stay.dto.StayListResponse;
import com.example.clonepjtairbb.stay.dto.StayOneResponse;
import com.example.clonepjtairbb.stay.service.StayService;
import com.example.clonepjtairbb.user.entity.User;
import com.example.clonepjtairbb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stay")
public class StayController {
    private final StayService stayService;
    private final UserRepository userRepository;

    //숙소 등록
    @PostMapping
    public ResponseEntity<Message> registerNewStay(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RegisterStayRequest registerStayRequest
    ){
        User user = userDetails.getUser();
        return stayService.registerNewStay(user, registerStayRequest);
    }

    //전체 숙소 조회(no filter)
    @GetMapping
    public ResponseEntity<List<StayListResponse>> getAllStay(
    ){
        return stayService.getAllStay();
    }

    //숙소 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<StayOneResponse> getStayById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(stayService.getStayById(id), HttpStatus.OK);
    }

<<<<<<< HEAD
//    @GetMapping("custom")
//    public ResponseEntity<List<StayOneResponse>> getSearchItem(
//            @RequestParam(value = "costPerDay", required = false) Integer cost,
//            @RequestParam(value = "title", required = false) String title){
//        return stayService.getSearchItem(cost, title);
//    }

    @GetMapping("custom")
    public ResponseEntity<List<StayListResponse>> getSearchItem(SearchOptionRequest request){
        return stayService.getSearchItem(request);
=======
    @PostMapping("/{stayId}")
    public ResponseEntity<Message> makeStayReservation(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long stayId,
            @RequestBody ReservationRequest reservationRequest
    ){
        User user = userDetails.getUser();
        return stayService.makeStayReservation(user, stayId, reservationRequest);
>>>>>>> c24a81511fa96bc063b7af75430377178f86af8d
    }
}

