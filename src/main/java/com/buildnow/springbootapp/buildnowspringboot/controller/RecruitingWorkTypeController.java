package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.RecruitingWorkType;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitingWorkTypeRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruiting-work-type")
public class RecruitingWorkTypeController{
    private final RecruitingWorkTypeRepository recruitingWorkTypeRepository;
    private final RecruitmentRepository recruitmentRepository;
    @GetMapping("/admin/{id}")
    public ResponseEntity<List<RecruitingWorkType>> getRecruitingWorkTypes(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 Recruitment가 없습니다."));
        List<RecruitingWorkType> recruitingWorkTypeList = recruitingWorkTypeRepository.findByRecruitment(recruitment);
        return new ResponseEntity<>(recruitingWorkTypeList, HttpStatus.OK);
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<List<RecruitingWorkType>> insertRecruitingWorkTypes(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 Recruitment가 없습니다."));
        String[] workTypeArr = {
                "골조공사 (건축)",
                "미장방수.조적.타일 (건축)",
                "도장공사 (건축)",
                "석공사 (건축)",
                "목재창호공사 (건축)",
                "AL창호공사 (건축)",
                "PL창호공사 (건축)",
                "내장목공사 (건축)",
                "잡철물공사 (건축)",
                "지붕공사 (건축)",
                "일반건설업 (건축)",
                "철골공사 (건축)",
                "데크플레이트 (건축)",
                "테라쪼 (건축)",
                "인테리어 (건축)",
                "세대현관문 (건축)",
                "커튼월,외장판넬 (건축)",
                "계단난간대 (건축)",
                "유리공사 (건축)",
                "PVC바닥재 (건축)",
                "도배공사 (건축)",
                "골조 (건축)",
                "미장/조적 (건축)",
                "방수 (건축)",
                "타일 (건축)",
                "도장 (건축)",
                "석공사 (건축)",
                "목재창호 (건축)",
                "AL창호 (건축)",
                "PL창호 (건축)",
                "유리 (건축)",
                "내장목 (건축)",
                "잡철물 (건축)",
                "지붕 (건축)",
                "도배/바닥재 (건축)",
                "건축시설물유지 (건축)",
                "경량콘크리트판넬 (건축)",
                "온돌/강화마루 (건축)",
                "파일항타[건축] (건축)",
                "조경식재 (건축)",
                "조경식재/조경시설물 (건축)",
                "안전시설물 (건축)",
                "가구/주방가구 (건축)",
                "데크플레이트 (건축)",
                "시스템루버 (건축)",
                "기타 (건축)",
                "일반토목 (토목)",
                "부대토목 (토목)",
                "포장공사 (토목)",
                "파일항타공사 (토목)",
                "조경시설물 (토목)",
                "상하수도 (토목)",
                "토목철물 (토목)",
                "철거공사 (토목)",
                "철강재 (토목)",
                "특수구조물 (토목)",
                "보링,연약지반 (토목)",
                "수중 (토목)",
                "준설 (토목)",
                "조경식재 (토목)",
                "법면보호 (토목)",
                "교좌장치 (토목)",
                "도로유관관로공사 (토목)",
                "철근콘크리트공사 (토목)",
                "방수 (토목)",
                "신축이음 (토목)",
                "일반토목 (토목)",
                "포장 (토목)",
                "상하수도 (토목)",
                "토목철물 (토목)",
                "파일항타[토목] (토목)",
                "토목시설물유지 (토목)",
                "기타 (토목)",
                "소방전기 (전기)",
                "정보통신 (전기)",
                "임시동력 (전기)",
                "전기일반 (전기)",
                "소방전기 (전기)",
                "정보통신 (전기)",
                "기타 (전기)",
                "소방설비 (기계)",
                "가스공사 (기계)",
                "자동제어 (기계)",
                "터널기계설비 (기계)",
                "조립식P.D (기계)",
                "기계일반 (기계)",
                "기계일반[소방포함] (기계)",
                "가스설비 (기계)",
                "자동제어 (기계)",
                "우수처리/정화조 (기계)",
                "냉난방기 (기계)",
                "실내환기설비 (기계)",
                "기타 (기계)",
                "건축시설물유지 (기타)",
                "토목시설물유지 (기타)",
                "지하수개발 (기타)",
                "기타 (기타)"
        };

        List<RecruitingWorkType> recruitingWorkTypeList = new ArrayList<>();
        for(String temp : workTypeArr){
            RecruitingWorkType recruitingWorkType = RecruitingWorkType.builder()
                    .workType(temp)
                    .build();
            recruitingWorkType.setRecruitment(recruitment);
            recruitment.addRecruitingWorkType(recruitingWorkType);
            recruitingWorkTypeRepository.save(recruitingWorkType);
            recruitingWorkTypeList.add(recruitingWorkType);
        }

        return new ResponseEntity<>(recruitingWorkTypeList, HttpStatus.OK);
    }
}
