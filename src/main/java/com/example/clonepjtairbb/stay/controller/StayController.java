package com.example.clonepjtairbb.stay.controller;

import com.example.clonepjtairbb.common.security.UserDetailsImpl;
import com.example.clonepjtairbb.common.utils.Message;
import com.example.clonepjtairbb.stay.dto.RegisterStayRequest;
import com.example.clonepjtairbb.stay.dto.StayListResponse;
import com.example.clonepjtairbb.stay.dto.StayOneResponse;
import com.example.clonepjtairbb.stay.entity.Stay;
import com.example.clonepjtairbb.stay.service.StayService;
import com.example.clonepjtairbb.user.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
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
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        User user = userDetails.getUser();
        return stayService.getAllStay(user);
    }

    //숙소 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<StayOneResponse> getStayById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(stayService.getStayById(id), HttpStatus.OK);
    }

    //필터 적용 조회
    // @GetMapping("/search")
    // public ResponseEntity<StayListResponse> searchStay(
    //     @RequestParam(Stay stay)
    // ){
    //     return new ResponseEntity<>(stayService.getStayById(stay), HttpStatus.OK);
    // }
}
