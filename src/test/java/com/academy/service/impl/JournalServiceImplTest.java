package com.academy.service.impl;

import com.academy.dto.JournalCreateDto;
import com.academy.dto.JournalDto;
import com.academy.dto.UserDto;
import com.academy.enums.ProcedureStatus;
import com.academy.enums.UserStatus;
import com.academy.mapper.JournalMapper;
import com.academy.model.entity.*;
import com.academy.model.repository.JournalRepository;
import com.academy.model.repository.UserRepository;
import com.academy.service.*;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class JournalServiceImplTest {

    @Autowired
    private JournalService journalService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private JournalRepository journalRepository;
    @MockBean
    private DiagnosisService diagnosisService;
    @MockBean
    private TreatmentService treatmentService;
    @MockBean
    private BillService billService;
    @Autowired
    private JournalMapper journalMapper;
    @MockBean
    private SecurityUtil securityUtil;
    private Integer testId;
    private UserDto userDto;
    private User user;
    private JournalCreateDto journalCreateDto;
    private Journal journal;
    private Bill bill;
    private Diagnosis diagnosis;
    private Treatment treatment;

    @BeforeEach
    void setUp() {
        journal = Journal.builder()
                .treatmentStatus(ProcedureStatus.PROCEDURE.name())
                .build();
        var journalList = Collections.singletonList(journal);
        testId = 1;

        userDto = new UserDto();
        userDto.setUsername("testUsername");
        userDto.setId(testId);

        user = User.builder()
                .username("testUsername")
                .id(testId)
                .build();

        journalCreateDto = new JournalCreateDto();
        journalCreateDto.setPatientsUsername("testPatient");
        journalCreateDto.setDiagnosisId(1);
        journalCreateDto.setTreatmentTypeId(1);

        diagnosis = new Diagnosis();
        treatment = new Treatment();
        var treatmentType = new TreatmentType();
        treatmentType.setPrice(100);
        treatment.setTreatmentType(treatmentType);
        journal = new Journal();
        journal.setTreatment(treatment);
        journal.setPatient(user);


        bill = new Bill();

        Mockito.when(journalRepository.findAllByPatientId(testId)).thenReturn(journalList);
        Mockito.doReturn(userDto).when(userService).findByUsername(userDto.getUsername());
        Mockito.when(userRepository.findByUsername(Mockito.isA(String.class))).thenReturn(user);
        Mockito.doReturn("testDoctor").when(securityUtil).getUsername();
        Mockito.when(userRepository.findByUsername("testDoctor")).thenReturn(user);
        Mockito.doReturn(diagnosis).when(diagnosisService).findById(Mockito.isA(Integer.class));
        Mockito.doReturn(treatment).when(treatmentService).createPrescription(journalCreateDto.getTreatmentTypeId());
        Mockito.when(securityUtil.hasRole(Role.ROLE_NURSE)).thenReturn(false);
        Mockito.doNothing().when(userService).save(Mockito.isA(User.class));
    }

    @Test
    void findAllByPatientIdTest() {
        var journalTest = journalService.findAllByPatientId(testId);
        assertEquals("PROCEDURE", journalTest.get(0).getTreatmentStatus());

    }

    @Test
    void findAllByPatientUsernameTest() {
        var journalTest = journalService.findAllByPatientUsername(userDto.getUsername());
        assertEquals("PROCEDURE", journalTest.get(0).getTreatmentStatus());
    }

    @Test
    void saveDiagnosisInJournalTest() {
        journalService = Mockito.mock(JournalServiceImpl.class);
        Mockito.doNothing().when(journalService).save(Mockito.isA(Journal.class));
        journalService.saveDiagnosisInJournal(journalCreateDto);
        Mockito.verify(journalService, Mockito.times(1)).saveDiagnosisInJournal(Mockito.isA(JournalCreateDto.class));
    }

    @Test
    void doProcedureTest() {
        journalService = Mockito.mock(JournalServiceImpl.class);
        Mockito.doReturn(journal).when(journalService).findById(1);
        Mockito.doReturn(journal).when(journalService).mapToProcedure(journal);
        Mockito.doNothing().when(journalService).save(journal);
        Mockito.doReturn(bill).when(billService).buildBill(Mockito.isA(Integer.class), Mockito.isA(User.class));
        Mockito.doNothing().when(billService).save(bill);
        journalService.doProcedure(1);
        Mockito.verify(journalService, Mockito.times(1)).doProcedure(Mockito.isA(Integer.class));

    }

    @Test
    void mapToProcedureTest() {
        var journalTest = journalService.mapToProcedure(journal);
        assertEquals(ProcedureStatus.PROCEDURE.name(), journalTest.getTreatmentStatus());
    }

    @Test
    void dischargeTest() {
        journalService = Mockito.mock(JournalServiceImpl.class);
        Mockito.doReturn(diagnosis).when(diagnosisService).findByName(Mockito.isA(String.class));
        Mockito.doReturn(treatment).when(treatmentService).createDischarge();
        Mockito.doNothing().when(journalService).save(Mockito.isA(Journal.class));
        journalService.discharge(journalCreateDto.getPatientsUsername());
        Mockito.verify(journalService, Mockito.times(1)).discharge(Mockito.isA(String.class));
    }

    @Test
    void moveToHospitalTest() {
        journalService.moveToHospital(journalCreateDto.getPatientsUsername());
        assertEquals(UserStatus.WAITING_FOR_ADMISSION.name(), user.getStatus());
    }

    @Test
    void saveAdmissionTest() {
        Mockito.doReturn(treatment).when(treatmentService).createAdmission();
        Mockito.doReturn(null).when(journalRepository).save(Mockito.isA(Journal.class));

        journalService.saveAdmission(journalCreateDto);
        assertEquals(UserStatus.ACTIVE.name(), user.getStatus());
    }

}