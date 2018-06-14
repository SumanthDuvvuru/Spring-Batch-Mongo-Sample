package com.dsr;

import java.util.List;
import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
	@Autowired
	ReportRepository repo;
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping("/run")
	public String launchJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(job, jobParameters);

		return "Batch job has been invoked";
	}

	@RequestMapping("/save")
	public String save() throws Exception {
		Report r = new Report();
		int num = new Random().nextInt();
		r.setId(num);
		r.setName("Mongo " + String.valueOf(num));
		repo.save(r);
		return "Save success :: id : " + r.getId();
	}

	@RequestMapping("/getall")
	public List<Report> get(String name) throws Exception {
		List<Report> r = repo.findAll();
		return r;
	}

	@RequestMapping("/delete/{id}")
	public String deleteById(String id) throws Exception {
		repo.deleteById(id);
		return "deleted " + id;
	}

	@RequestMapping("/deleteall")
	public String deleteall() throws Exception {
		repo.deleteAll();
		return "deleted all docs";
	}

}
