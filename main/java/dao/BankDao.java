package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.BankModel;
import util.ConnectionFactory;

public class BankDao {

    public ArrayList<BankModel> listBanks() {

        ArrayList<BankModel> list =
                new ArrayList<>();

        String sql =
                "SELECT id_bnk, name_bnk, contact_bnk, img_bnk " +
                "FROM tb_bank " +
                "ORDER BY name_bnk";

        try (
            Connection conn =
                    ConnectionFactory.getConnection();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery()
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt("id_bnk");

                String name =
                        rs.getString("name_bnk");

                String contact =
                        rs.getString("contact_bnk");

                String img =
                        rs.getString("img_bnk");

                BankModel bank =
                        new BankModel(
                                id,
                                name,
                                contact,
                                img
                        );

                list.add(bank);
            }

        } catch (Exception e) {

            System.out.println(
                    "ERRO AO LISTAR BANCOS:"
            );

            e.printStackTrace();
        }

        return list;
    }
    
    
    
    
    public BankModel findById(int id) {

        String query =
                "SELECT id_bnk, name_bnk, contact_bnk, img_bnk " +
                "FROM tb_bank " +
                "WHERE id_bnk = ?";

        try (
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)
        ) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int idBank =
                            rs.getInt("id_bnk");

                    String name =
                            rs.getString("name_bnk");

                    String contact =
                            rs.getString("contact_bnk");

                    String img =
                            rs.getString("img_bnk");

                    return new BankModel(
                            idBank,
                            name,
                            contact,
                            img
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

}
