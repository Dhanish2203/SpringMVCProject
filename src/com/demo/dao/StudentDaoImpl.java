package com.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.demo.model.Student;

public class StudentDaoImpl implements StudentDao {

	private JdbcTemplate jdbcTemplate;

	public StudentDaoImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public int create(Student student) {

		String sql = "insert into student(stud_name,stud_email,stud_course) values(?,?,?)";

		try {

			int counter = jdbcTemplate.update(sql,
					new Object[] { student.getName(), student.getEmail(), student.getCourse() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Student> read() {
		List<Student> studentList = jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();

				student.setId(rs.getInt("stud_id"));
				student.setName(rs.getString("stud_name"));
				student.setEmail(rs.getString("stud_email"));
				student.setCourse(rs.getString("stud_course"));

				return student;
			}

		});

		return studentList;
	}

	@Override
	public List<Student> findStudentById(int studentId) {

		List<Student> studentList = jdbcTemplate.query("SELECT * FROM STUDENT where stud_id=?",
				new Object[] { studentId }, new RowMapper<Student>() {

					@Override
					public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
						Student student = new Student();

						student.setId(rs.getInt("stud_id"));
						student.setName(rs.getString("stud_name"));
						student.setEmail(rs.getString("stud_email"));
						student.setCourse(rs.getString("stud_course"));

						return student;
					}

				});

		return studentList;
	}

	@Override
	public int update(Student student) {
		String sql = "update  student set stud_name=?, stud_email=?, stud_course=? where stud_id=?";

		try {

			int counter = jdbcTemplate.update(sql,
					new Object[] { student.getName(), student.getEmail(), student.getCourse(), student.getId() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int studentId) {
		String sql = "delete from student where stud_id=?";

		try {

			int counter = jdbcTemplate.update(sql, new Object[] { studentId });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}