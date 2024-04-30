package com.br.unisales.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.unisales.table.Usuario;

/**
 * @apiNote Classe responsável por realizar o CRUD na tabela usuario do banco de dados vendas.db
 * 
 * @author Vito Rodrigues Franzosi
 * @Data Criação 10.04.2024
 */
public class UsuarioRepository implements GenericoRepository<Usuario> {
    private Connection conn;

    /**
     * @apiNote Método construto da classe
     * @param Connection conn
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public UsuarioRepository(Connection conn) {
        this.conn=conn;
    }

    /**
     * @apiNote Método responsável por buscar todos os usuários no banco de dados
     * @return List<Usuario>
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    @Override
    public List<Usuario> findByAll() {
        try {
            String sql = "SELECT * FROM usuario ORDER BY nome, email";
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            return this.getDadosUsuarios(rs);
        } catch (SQLException e) {
            System.err.println("Erro no método findByAll() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Não foi possível implementar o método findByAll()  da classe UsuarioRepository.");
    }

    /**
     *@apiNote Método responsável por salvar os dados do usuário no banco de dados
     * @param Usuario usuario
     * @return Usuario
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    @Override
    public Usuario save(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario(nome, email, senha, grupo) VALUES(?, ?, ?, ?)";
            if((usuario.getId()!=null) && (usuario.getId()>0))
                sql = "UPDATE usuario set nome=?, email=?, senha=?, grupo=? WHERE id = ?";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setString(4, usuario.getGrupo());
            if((usuario.getId()!=null) && (usuario.getId()>0))
                pstm.setInt(5, usuario.getId());
            if(pstm.executeUpdate()==1) {
                System.out.println("Os dados do usuário "+usuario.getNome()+" foram salvos com sucesso!");
                return this.findByEmail(usuario.getEmail());
            } else {
                System.err.println("Os dados do usuário "+usuario.getNome()+" NÃO foram salvos!");
                return new Usuario();
            } 
        } catch (Exception e) {
            System.err.println("Erro no método save() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @apiNote Método responsável por excluir os dados de um usuário no banco de dados
     * @param Integer id
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM usuario WHERE id=?";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            if(pstm.executeUpdate()==1)
                System.out.println("Os dados do usuário de código "+id+" foram excluídos com sucesso!");
            else
                System.err.println("Os dados do usuário de código "+id+" NÃO foram excluídos!");
        } catch (SQLException e) {
            System.err.println("Erro no método delete() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por buscar um usuário pelo id
     * @param Integer id
     * @return Usuario
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    @Override
    public Usuario findById(Integer id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id=?";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            return this.getDadosUsuario(rs);
        } catch (SQLException e) {
            System.err.println("Erro no método findById() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @apiNote Método responsável por buscar um usuário no banco de dados pelo seu e-mail
     * @param String email
     * @return Usuario
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public Usuario findByEmail(String email) {
        try {
            String sql = "SELECT * FROM usuario WHERE email=?";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            return this.getDadosUsuario(rs);
        } catch (Exception e) {
            System.err.println("Erro no método findByEmail() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @apiNote Método responsável por buscar um ou mais usuários no bando de dados
     * @param Usuario usuario
     * @return List<Usuario>
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public List<Usuario> findByParameters(Usuario usuario) {
        try {
            String sql = "SELECT * FROM usuario WHERE grupo IS NOT NULL";
            if(usuario.getNome()!=null)
                sql +=" AND nome like ? ";
            if(usuario.getEmail()!=null)
                sql +=" AND email=? ";
            if(usuario.getGrupo()!=null)
                sql +=" AND grupo=? ";
            sql +=" ORDER BY nome, email";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            if((usuario.getNome()!=null) && (usuario.getEmail()!=null) && (usuario.getGrupo()!=null)) {
                pstm.setString(1, "%"+usuario.getNome()+"%");
                pstm.setString(2, usuario.getEmail());
                pstm.setString(3, usuario.getGrupo());
            } else if((usuario.getNome()!=null) && (usuario.getEmail()!=null) && (usuario.getGrupo()==null)) {
                pstm.setString(1, "%"+usuario.getNome()+"%");
                pstm.setString(2, usuario.getEmail());
            } else if((usuario.getNome()!=null) && (usuario.getEmail()==null) && (usuario.getGrupo()!=null)) {
                pstm.setString(1, "%"+usuario.getNome()+"%");
                pstm.setString(2, usuario.getGrupo());
            } else if((usuario.getNome()!=null) && (usuario.getEmail()==null) && (usuario.getGrupo()==null)) {
                pstm.setString(1, "%"+usuario.getNome()+"%");
            } else if((usuario.getNome()==null) && (usuario.getEmail()!=null) && (usuario.getGrupo()!=null)) {
                pstm.setString(1, usuario.getEmail());
                pstm.setString(2, usuario.getGrupo());
            } else if((usuario.getNome()==null) && (usuario.getEmail()!=null) && (usuario.getGrupo()==null)) {
                pstm.setString(1, usuario.getEmail());
            } else if((usuario.getNome()==null) && (usuario.getEmail()==null) && (usuario.getGrupo()!=null)) {
                pstm.setString(1, usuario.getGrupo());
            }
            ResultSet rs = pstm.executeQuery();
            return this.getDadosUsuarios(rs);
        } catch (SQLException e) {
            System.err.println("Erro no método findByParametros() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @apiNote Método responsável por tranformar os dados do ResultSet na classe Usuario
     * @param ResultSet rs
     * @return Usuario
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private Usuario getDadosUsuario(ResultSet rs) {
        try {
            Usuario usuario = new Usuario();
            if(rs.next()) {
                usuario.setEmail(rs.getString("email"));
                usuario.setGrupo(rs.getString("grupo"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
            }
            return usuario;
        } catch (Exception e) {
            System.err.println("Erro no método getDadosUsuario() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @apiNote Método responsável por tranformar os dados do ResultSet em uma lista da classe Usuario
     * @param ResultSet rs
     * @return List<Usuario>
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private List<Usuario> getDadosUsuarios(ResultSet rs) {
        try {
            List<Usuario> lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setGrupo(rs.getString("grupo"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                lista.add(usuario);
            }
            return lista;
        } catch (Exception e) {
            System.err.println("Erro no método getDadosUsuarios() da classe UsuarioRepository: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
