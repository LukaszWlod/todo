package com.luwl.todo.repository;

import com.luwl.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    int countAllByCompleted(boolean completed);
}
