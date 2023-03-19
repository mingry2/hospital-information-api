package com.mustache.bbs1.controller.ui;

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
@RequestMapping(value = "hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

	private final HospitalRepository hospitalRepository;

	//병,의원 정보 전체 조회
	@GetMapping(value = "/list")
	public String list(@PageableDefault(size = 10, sort = "id") Pageable pageable, Model model) {
		Page<Hospital> hospitals = hospitalRepository.findAll(pageable);

		model.addAttribute("hospitals", hospitals);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());

		return "hospitals/list";
	}

	//병,의원 정보 상세 조회(단건)
	@GetMapping(value = "/{hospitalId}")
	public String get(@PathVariable Long hospitalId, Model model) {
		Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

		if (!optionalHospital.isEmpty()) {
			model.addAttribute("hospital", optionalHospital.get());

			return "hospitals/detail";
		} else {
			model.addAttribute("message", String.format("%d번의 병/의원을 찾을 수 없습니다!", hospitalId));

			return "hospitals/error";
		}
	}

	//특정 키워드로 검색
	@GetMapping(value = "/search")
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
