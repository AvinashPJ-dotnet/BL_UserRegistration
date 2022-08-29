package com.bridgelab.scheduler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgelab.notes.model.Note;
import com.bridgelab.notes.repository.NoteRepository;

@Component
public class SchedulerService {
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	private Environment environment;
	LocalDate todayDate = LocalDate.now();

//	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
	@Scheduled(fixedRate = 1000 * 10)
	public void scheduleDeleteTrashNotes() {
		int trashLimit = Integer.parseInt(environment.getProperty("TRASH_LIMIT"));

		List<Note> trashNotes = noteRepository.findAll().stream().filter(note -> note.isDeleted() == true)
				.collect(Collectors.toList());
		for (Note eachNote : trashNotes) {
			int result = (int) ChronoUnit.DAYS.between(eachNote.getDeletedDate(), todayDate);
			if (result >= trashLimit) {
				noteRepository.deleteById(eachNote.getId());
			}
		}
	}
}
