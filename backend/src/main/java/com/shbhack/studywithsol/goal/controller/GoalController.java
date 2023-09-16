package com.shbhack.studywithsol.goal.controller;

import com.shbhack.studywithsol.goal.dto.GoalDto;
import com.shbhack.studywithsol.goal.service.GoalService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/goal")
public class GoalController {
    private final GoalService goalService;

    @ApiOperation(value ="장기 목표 등록")
    @PostMapping
    public ResponseEntity<String> registerLongGoal(
            Authentication authentication,  @RequestBody GoalDto.GoalReqDto goalReqDto){
        Long parentId = Long.valueOf(authentication.getName());
        String str = goalService.registerLongGoal(parentId, goalReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @ApiOperation(value ="장기 목표 삭제")
    @PatchMapping("/{goalId}")
    public ResponseEntity<String> deleteLongGoal(@PathVariable Long goalId){
        String str = goalService.deleteLongGoal(goalId);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @ApiOperation(value = "자녀 장기 목표 조회")
    @GetMapping("/child")
    public ResponseEntity<List<GoalDto.GoalRespDto>> getLongGoal(Authentication authentication){
        Long childrenId = Long.valueOf(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(goalService.getLongGoal(childrenId));
    }

    @ApiOperation(value = "부모 장기 목표 조회")
    @GetMapping("/parent")
    public ResponseEntity<GoalDto.GoalRespDto> getLongGoalParent(Authentication authentication, @RequestBody GoalDto.GetGoalReqDto getGoalReqDto){
        GoalDto.GoalRespDto goalRespDto = goalService.getLongGoalParent(Long.valueOf(authentication.getName()), getGoalReqDto.getChildrenId());
        return ResponseEntity.status(HttpStatus.OK).body(goalRespDto);
    }
}
