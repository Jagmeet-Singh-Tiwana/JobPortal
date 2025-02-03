package com.jagmeet.jobportal.services;

import com.jagmeet.jobportal.entity.JobSeekerProfile;
import com.jagmeet.jobportal.entity.RecruiterProfile;
import com.jagmeet.jobportal.entity.Users;
import com.jagmeet.jobportal.repository.JobSeekerProfileRepository;
import com.jagmeet.jobportal.repository.RecruiterProfileRepository;
import com.jagmeet.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService{

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository=jobSeekerProfileRepository;
        this.recruiterProfileRepository=recruiterProfileRepository;
    }
    public Users addNew(Users users)
    {
        System.out.println("line 30"+users);
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(users);
//        int userTypeId = users.getUserTypeId().getUserTypeId();
        System.out.println("service user is: "+users);
        System.out.println(users.getUserTypeId());
        //System.out.println(users.getUserTypeId().getUserTypeId());
//        int userTypeId=users.getUserTypeId().getUserTypeId();
        int userTypeId=1;
        System.out.println(userTypeId);
        if(userTypeId == 1)
        {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }
    public Optional<Users> getUserByEmail(String email)
    {
        return usersRepository.findByEmail(email);
    }
}

