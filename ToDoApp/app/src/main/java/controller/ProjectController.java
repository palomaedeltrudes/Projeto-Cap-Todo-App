package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {

    public void save(Project project) {

   String sql = "INSERT INTO project(name, description, createdAt, updatedAt)"
           + " VALUES (?, ?, CURRENT_DATE, CURRENT_DATE)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE project SET "
                + "name = ?, "
                + "description = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //estabelecendo a conexao com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setInt(4, project.getId());
            
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
        
    public List<Project> getAll() {

        String sql = "SELECT * FROM project";

        //lista de tarefas que sera devolvida quando a chamada de metodo acontecer.
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;        

        try {
            //criacao da conexao
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);            

            //valor retornado pela execucao da query
            resultSet = statement.executeQuery();

            //enquanto houver valor a ser percorrido no meu resultSet
            while (resultSet.next()) {

                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                projects.add(project);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        //lista de projetos que foi criada e carregada no banco de dados
        return projects;
    }
        

    public void removeById(int IdProject) {

        String sql = "DELETE FROM project WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, IdProject);
            
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o projeto ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

}

