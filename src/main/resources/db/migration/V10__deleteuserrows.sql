alter table user
    drop foreign key user_ibfk_2,
    drop column admission_date,
    drop column discharge_date,
    drop column final_diagnosis_id;