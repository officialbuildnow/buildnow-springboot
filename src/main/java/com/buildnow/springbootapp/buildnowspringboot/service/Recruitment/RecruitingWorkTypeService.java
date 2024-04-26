package com.buildnow.springbootapp.buildnowspringboot.service.Recruitment;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType.RecruitingWorkTypeDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType.RecruitingWorkTypeListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.RecruitingWorkType;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.RequiringPatent;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitingWorkTypeRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RequiringPatentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitingWorkTypeService {
    private final RecruitingWorkTypeRepository recruitingWorkTypeRepository;
    private final RequiringPatentRepository requiringPatentRepository;
    private final RecruitmentRepository recruitmentRepository;
    //신한 공종별 면허 초기화
    @Transactional
    public void initialGradingOfShinHan(Long recruitmentId, RecruitingWorkTypeListDTO recruitingWorkTypeListDTO){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("일치하는 recruitment가 존재하지 않습니다."));

        for(RecruitingWorkTypeDTO temp : recruitingWorkTypeListDTO.getRecruitingWorkTypeDTOs()){
            if(recruitingWorkTypeRepository.existsByRecruitmentAndWorkType(recruitment, temp.getRecruitingWorkType())){
                throw new RuntimeException("중복된 공종이 삽입되었습니다. 입력을 점검해주세요");
            }
            RecruitingWorkType newWorkType = RecruitingWorkType.builder()
                    .workType(temp.getRecruitingWorkType())
                    .build();
            recruitment.addRecruitingWorkType(newWorkType);
            for(String patentName : temp.getPatentListByRecruitingWorkType().getPatentList()){
                RequiringPatent newPatent = RequiringPatent.builder()
                        .patentName(patentName)
                        .build();
                newWorkType.addRequiringPatent(newPatent);
            }
        }
    }

    @Transactional
    public List<RecruitingWorkType> retrieveRecruitingWorkType(Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("일치하는 recruitment가 존재하지 않습니다."));
        return recruitment.getRecruitingWorkTypeList();
    }
}
