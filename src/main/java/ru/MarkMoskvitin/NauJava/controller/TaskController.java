package ru.MarkMoskvitin.NauJava.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.MarkMoskvitin.NauJava.repo.TaskRepository;


@Controller
@RequestMapping("/custom/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/view")
    public ModelAndView getAllView() throws Exception
    {
        ModelAndView model = new ModelAndView("taskList");
        if (taskRepository.findAll().toString()==null)
            throw new ResourceNotFoundException("Tasks not found");
        model.addObject("tasks", taskRepository.findAll());
        return model;
    }


    @GetMapping("/find/user/{username}")
    public ModelAndView findByUsernameView(@PathVariable String username) throws Exception
    {
        ModelAndView model = new ModelAndView("taskList");
        if (taskRepository.findByUser(username).isEmpty())
            throw new ResourceNotFoundException("This user doesn't have any tasks");
        model.addObject("tasks", taskRepository.findByUser(username));
        return model;
    }

    @GetMapping("/find/group/{groupTitle}")
    public ModelAndView findByGroupView(@PathVariable String groupTitle) throws Exception
    {
        ModelAndView model = new ModelAndView("taskList");
        if (taskRepository.findByGroup(groupTitle).isEmpty())
            throw new ResourceNotFoundException("This group doesn't have any tasks");
        model.addObject("tasks", taskRepository.findByGroup(groupTitle));
        return model;
    }

    @GetMapping("/find/tof/t={title}&f={finish}")
    public ModelAndView findByTitleOrFinishView(@PathVariable String title,@PathVariable String finish)
    {
        ModelAndView model = new ModelAndView("taskList");
        if (taskRepository.findByTitleOrFinish(title,finish).isEmpty())
            throw new ResourceNotFoundException("Tasks not found");
        model.addObject("tasks", taskRepository.findByTitleOrFinish(title,finish));
        return model;
    }
}
