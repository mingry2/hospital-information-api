package com.mustache.bbs1.controller.ui;

import com.mustache.bbs1.domain.dto.hospital.HospitalDto;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    // 새 글작성 페이지
    @GetMapping("/new")
    public String createHospital(){
        return "hospitals/new";
    }

    // 게시글 찾는 페이지
    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);

        if (!optionalHospital.isEmpty()) {
            model.addAttribute("hospital", optionalHospital.get());
            return "hospitals/show";
        }else {
            model.addAttribute("message", String.format("%d번을 찾을 수 없습니다!", id));
            return "hospitals/error";
        }
    }

    // new에서 입력한 데이터 DB로 담아오는 메서드
    @PostMapping("/posts")
    public String addHospital(HospitalDto hospitalDto){
        Hospital savedHospital = hospitalRepository.save(hospitalDto.toEntity());
        return String.format("redirect:/hospitals/%d", savedHospital.getId());
    }

    // 모든 게시글 테이블 리스트로 보는 페이지
    @GetMapping("")
    public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable, Model model){
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size: {}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }

    // home 페이지
    @GetMapping("/home")
    public String homeHospital(){
        return "hospitals/home";
    }

    // id의 게시글 수정하는 페이지
    @GetMapping("/{id}/edit")
    public String editHospital(@PathVariable Integer id, Model model){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        log.info("Optional hospital: {}", optionalHospital);

        if (!optionalHospital.isEmpty()){
            model.addAttribute("hospital",optionalHospital.get());
            return "hospitals/edit";
        }else {
            model.addAttribute("message", String.format("%d번을 찾을 수 없습니다!", id));
            return "hospitals/error";
        }
    }

    // edit 한 데이터 update해서 db에 넣는 메서드
    @PostMapping("/{id}/update")
    public String updateHospital(@PathVariable Integer id, HospitalDto hospitalDto, Model model) {
        log.info("id:{} hospitalName:{} loadNameAddress:{}", hospitalDto.getId(), hospitalDto.getHospitalName(),hospitalDto.getRoadNameAddress());
        Hospital savedHospital = hospitalRepository.save(hospitalDto.toEntity());
        model.addAttribute("hospital", savedHospital);
        return String.format("redirect:/hospitals/%d", id);
    }

    // id 게시글 삭제
    @GetMapping("/{id}/delete")
    public String deleteHospital(@PathVariable Integer id){
        hospitalRepository.deleteById(id);
        return "redirect:/hospitals/";
    }

    // 특정 키워드로 검색기능 추가
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Pageable pageable, Model model) {
        log.info("keyword: {}", keyword);
        Page<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "hospitals/list";
    }
}
