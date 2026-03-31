package com.jpahibernate.example.hospital_management_system.service;

import com.jpahibernate.example.hospital_management_system.model.Doctor;
import com.jpahibernate.example.hospital_management_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // it contains business logic
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public String saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        return "Doctor saved successfully";
    }

    public List<Doctor> getAllDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList;
    }

    public Doctor getDoctorById(int id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isPresent()){
            return doctorOptional.get();
        } else {
            return null;
        }
    }

    public String countDoctors(){
        long totalCount = doctorRepository.count();
        return "Total doctors present are : "+totalCount;
    }

    public String deleteDoctorById(int id){
        doctorRepository.deleteById(id);
        return "Doctor with id : "+id +" is deleted successfully";
    }

    //update using put operation - updates complete object
    public String updateDoctorUsingPut(int doctorId, Doctor newDoctorRequest){
        // find doctor with id
        // if doctor is present, update it
        // else we cannot update
        Doctor existingDoctor = getDoctorById(doctorId);
        if(existingDoctor!=null){
            // proceed to update
            doctorRepository.save(newDoctorRequest);
            return "Doctor updated successfully";
        } else {
            // cannot update
            return "Doctor with id : "+doctorId+" is not present, hence cannot update";
        }
    }


    //update using patch operation - single specific fields
    public String updateDoctorUsingPatch(int doctorId, String newMobile, String newEmail){
        // find doctor with id
        // if doctor is present, update it
        // else we cannot update
        Doctor existingDoctor = getDoctorById(doctorId);
        if(existingDoctor!=null){
            // proceed to update
            existingDoctor.setEmail(newEmail);
            existingDoctor.setMobile(newMobile);
            doctorRepository.save(existingDoctor);
            return "Doctor updated successfully";
        } else {
            // cannot update
            return "Doctor with id : "+doctorId+" is not present, hence cannot update";
        }
    }


}
