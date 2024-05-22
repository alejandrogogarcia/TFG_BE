package es.udc.tfg.app.test.service.noteService;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.tfg.app.service.noteservice.NoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class noteServiceTest {
	
	@Autowired
	NoteService noteService;
	

}
