package ToDoApp;

import controller.ProjectController;
import model.Project;


public class App {

    public static void main(String[] args) {

        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);

//        project.setName("Novo nome do projeto");
//        projectController.update(project);
//        
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos = " + projects.size());        
//        Connection c = ConnectionFactory.getConnection();
//        ConnectionFactory.closeConnection(c);
    }

}
