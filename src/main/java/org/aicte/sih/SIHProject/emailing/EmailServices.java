package org.aicte.sih.SIHProject.emailing;

import org.aicte.sih.SIHProject.api.college.dto.entity.College;
import org.aicte.sih.SIHProject.api.faculty.dto.Entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmailServices {

    @Autowired
    private SmtpEmailSender smtpEmailSender;

    public void sendCollegeRegistrationSuccessfulEmail(College college) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Registration Successful | SHIKSHAK SETU");
        email.setTo(Collections.singletonList(college.getEmail()));
        String message = "Dear College,\n" +
                "Congratulations on your successful registration on our portal.  \n" +
                "Hey! Now getting faculties and filling vacancy is as simple as a click. Our automated recommendation system will find the best replacement available from the entire country for your institute.\n\n" +
                "College UIN: " + college.getUin() + "\n" +
                "Best Regards,\n" +
                "Team Shikshak Setu";

        /*String body = college.getName() + " has been successfully registered!\n " +
                "College UIN: " + college.getUin();*/
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void sendShortlistedEmail(Faculty faculty, College college) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Congratulations | SHIKSHAK SETU");
        email.setTo(Collections.singletonList(faculty.getEmailAddress()));
        String message = "Dear " + faculty.getFirstName() + " " + faculty.getLastName() + "\n" +
                "Congratulations on being shortlisted for the teaching job post you applied for. We wish you all the best for the upcoming procedures.\n\n" +
                "Teaching Post Details:- \n" +
                "College Name: " + college.getName() + "\n" +
                "Address: " + college.getCity() + ", " + college.getState() + "\n" +
                "Contact Details: \n" +
                "Email Address: " + college.getEmail() + "\n" +
                "Phone: " + college.getPhone() + "\n";
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void sendNotificationToFaculty(Faculty faculty, String message, String subject) {
        MessageEmail email = new MessageEmail();
        email.setSubject(subject);
        email.setTo(Collections.singletonList(faculty.getEmailAddress()));
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void sendFacultyRegistrationSuccessfulEmail(Faculty faculty) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Successfully Registered | SHIKSHAK SETU");
        email.setTo(Collections.singletonList(faculty.getEmailAddress()));
        String message ="Dear " + faculty.getFirstName() + " " + faculty.getLastName() + ",\n" +
                "Congratulations on your successful registration on our portal.  \n" +
                "Hey! Let’s take your career to greater height with Shikshak Setu. Don’t worry we are here to automate your hiring process. Now seat back and relax while we find the best opportunity for your career.\n" +
                "On being selected you may expect a mail from us. \n" +
                "Best Regards,\n" +
                "Team Shikshak Setu";
        //String message = faculty.getFirstName() + " " + faculty.getLastName() + " has been successfully registered as a faculty";
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    /*public void sendAppliedSuccessfullyEmail(AppliedJob appliedJob) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Successfully Applied For Job Post | Faculty Recruitment System");
        email.setTo(Collections.singletonList(appliedJob.getFaculty().getEmailAddress()));
        String message = appliedJob.getFaculty().getFirstName() + " " + appliedJob.getFaculty().getLastName() + " you have successfully applied for \n" +
                "Job Post Details:\n" +
                appliedJob.getAppliedPost().getHeading() + "\n" +
                appliedJob.getAppliedPost().getDescription() + "\n" +
                appliedJob.getAppliedPost().getCollegeName() + "\n" +
                "\n" +
                "All the best 👍";
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void sendNewApplicantEmail(AppliedJob appliedJob) {
        MessageEmail email = new MessageEmail();
        email.setSubject("NEW APPLICANT FOR THE TEACHING POST | Faculty Recruitment System");
        email.setTo(Collections.singletonList(appliedJob.getAppliedPost().getCollege().getEmail()));
        String message = "Your Job Post for " + appliedJob.getAppliedPost().getHeading() + " has received a new applicant \n" +
                "\n" +
                "Applicant Faculty Details: \n" +
                "Full Name: " + appliedJob.getFaculty().getFirstName() + " " + appliedJob.getFaculty().getLastName() + "\n" +
                "Email Address: " + appliedJob.getFaculty().getEmailAddress() + "\n" +
                "Phone Number: " + appliedJob.getFaculty().getPhoneNumber() + "\n" +
                "\n" +
                "Cover Letter Submitted: \n" +
                "" + appliedJob.getCoverLetter();

        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void sendShortlistedEmail(Faculty faculty, JobPost jobPost) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Congratulations | Faculty Recruitment System");
        email.setTo(Collections.singletonList(faculty.getEmailAddress()));
        String message = "Dear " + faculty.getFirstName() + " " + faculty.getLastName() + "\n" +
                "Congratulations on being shortlisted for the teaching job post you applied for. We wish you all the best for the upcoming procedures.\n\n" +
                "Teaching Post Details:- \n" +
                "College Name: " + jobPost.getCollegeName() + "\n" +
                "Address: " + jobPost.getCity() + ", " + jobPost.getState() + "\n" +
                "Contact Details: \n" +
                "Email Address: " + jobPost.getCollege().getEmail() + "\n" +
                "Phone: " + jobPost.getCollege().getPhone() + "\n" +
                jobPost.getHeading() + "\n" +
                jobPost.getDescription() + "\n" +
                "Total Applications: " + jobPost.getTotalApplicants();
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }

    public void newJobPostedEmail(JobPost jobPost) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Congratulations | Faculty Recruitment System");
        email.setTo(Collections.singletonList(jobPost.getCollege().getEmail()));
        String message = "New Teaching Post Successfully Posted \n\n" +
                "Teaching Post Details:- \n" +
                "College Name: " + jobPost.getCollegeName() + "\n" +
                "Address: " + jobPost.getCity() + ", " + jobPost.getState() + "\n" +
                "Contact Details: \n" +
                "Email Address: " + jobPost.getCollege().getEmail() + "\n" +
                "Phone: " + jobPost.getCollege().getPhone() + "\n" +
                jobPost.getHeading() + "\n" +
                jobPost.getDescription() + "\n";
        email.setBody(message);
        email.setHtml(false);
        smtpEmailSender.sendMessage(email);
    }*/

}
