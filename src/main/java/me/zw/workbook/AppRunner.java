package me.zw.workbook;

import me.zw.workbook.domain.JournalEntry;
import me.zw.workbook.repository.JournalRepository;
import me.zw.workbook.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountService.createAccount("springboot", "isawesome");
        accountService.createAccount("springsecurity", "isawesometoo");

        journalRepository.save(
                new JournalEntry("스프링 부트 입문", "스프링 부트를 시작", "2016-01-02 00:00:00.00")
        );
        journalRepository.save(
                new JournalEntry("스프링 부트 프로젝트", "프로젝트를 시작", "2016-01-03 00 :00:00.00")
        );
        journalRepository.save(
                new JournalEntry("스프링 부트 해부", "자세히 살펴봄", "2016-02-02 00:00:00.00")
        );
        journalRepository.save(
                new JournalEntry("스프링 부트 클라우드", "클라우드 파운드리", "2016-02-05 00:00:00.00")
        );
    }
}
