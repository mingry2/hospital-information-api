package com.mustache.bbs1.controller.ui;

import com.mustache.bbs1.domain.dto.hospital.HospitalCreateRequest;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

	private final HospitalRepository hospitalRepository;

	//병,의원 정보 상세 조회(단건)
	@GetMapping("/{hospitalId}")
	public String findById(@PathVariable Long hospitalId, Model model) {
		Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

		if (!optionalHospital.isEmpty()) {
			model.addAttribute("hospital", optionalHospital.get());
			return "hospitals/show";
		} else {
			model.addAttribute("message", String.format("%d번을 찾을 수 없습니다!", hospitalId));
			return "hospitals/error";
		}

	}

	//병,의원 정보를 전체 조회
	@GetMapping("")
	public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable, Model model) {
		Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
		log.info("size: {}", hospitals.getSize());
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
		return "hospitals/list";
	}

	//home 페이지
	@GetMapping("/home")
	public String homeHospital() {
		return "hospitals/home";
	}

	//리뷰 수정 페이지
	@GetMapping("/{id}/edit")
	public String editHospital(@PathVariable Integer id, Model model) {
		Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
		log.info("Optional hospital: {}", optionalHospital);

		if (!optionalHospital.isEmpty()) {
			model.addAttribute("hospital", optionalHospital.get());
			return "hospitals/edit";
		} else {
			model.addAttribute("message", String.format("%d번을 찾을 수 없습니다!", id));
			return "hospitals/error";
		}
	}

	//수정한 데이터 DB에 넣는 페이지
	@PostMapping("/{id}/update")
	public String updateHospital(@PathVariable Integer id,
			HospitalCreateRequest hospitalCreateRequest, Model model) {
		log.info("id:{} hospitalName:{} loadNameAddress:{}", hospitalCreateRequest.getId(),
				hospitalCreateRequest.getHospitalName(),
				hospitalCreateRequest.getRoadNameAddress());
		Hospital savedHospital = hospitalRepository.save(hospitalCreateRequest.toEntity());
		model.addAttribute("hospital", savedHospital);
		return String.format("redirect:/hospitals/%d", id);
	}

	//리뷰 삭제 페이지
	@GetMapping("/{id}/delete")
	public String deleteHospital(@PathVariable Integer id) {
		hospitalRepository.deleteById(id);
		return "redirect:/hospitals/";
	}

	//특정 키워드로 검색
	@GetMapping("/search")
	public String search(@RequestParam String keyword, Pageable pageable, Model model) {
		log.info("keyword: {}", keyword);
		Page<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword,
				pageable);
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("keyword", keyword);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());

		return "hospitals/list";
	}
}
