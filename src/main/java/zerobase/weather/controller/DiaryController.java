package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "날씨 일기 API", description = "일기 CRUD API입니다.")
@RestController
public class DiaryController { // 클라이언트에 어떤 API를 제공해야 할지
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 작성", description = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @Parameter(name = "date", description = "작성할 일기의 날짜", example = "2020-02-02")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "일기 조회", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @Parameter(name = "date", description = "조회할 날짜", example = "2020-02-02")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "일기 조회", description = "선택한 기간중의 모든 일기 데이터를 가져옵니다")
    @Parameter(name = "startDate", description = "조회할 기간의 첫번째날", example = "2020-02-02")
    @Parameter(name = "endDate", description = "조회할 기간의 마지막날", example = "2020-02-02")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "일기 수정", description = "해당하는 날짜의 일기 내용을 수정합니다.")
    @Parameter(name = "date", description = "수정할 일기의 날짜", example = "2020-02-02")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "일기 삭제", description = "입력한 날짜에 해당하는 일기 데이터를 삭제합니다.")
    @Parameter(name = "date", description = "삭제할 일기의 날짜", example = "2020-02-02")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
