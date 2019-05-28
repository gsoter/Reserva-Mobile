package a.android.reservamob.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Database extends SQLiteOpenHelper implements BaseColumns {

    private static final String NOME_BANCO = "ReservaDB";
    private static final int VERSAO_BANCO = 1;

    private static final String TABELA_CLIENTE = "Cliente";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_USUARIO = "usuario";
    private static final String COLUNA_SENHA = "senha";


    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABELA_CLIENTE + "(" +
                        COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_NOME + " VARCHAR(50) , " +
                        COLUNA_CPF + " VARCHAR(15)," +
                        COLUNA_USUARIO + " VARCHAR(50)," +
                        COLUNA_SENHA + " VARCHAR(15));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CLIENTE);
        onCreate(db);
    }

    public boolean inserirCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(COLUNA_ID, cliente.getId());
            values.put(COLUNA_NOME, cliente.getNome());
            values.put(COLUNA_CPF, cliente.getCpf());
            values.put(COLUNA_USUARIO, cliente.getUsuario());
            values.put(COLUNA_SENHA, cliente.getSenha());
            db.insert(TABELA_CLIENTE, null, values);
            return true;
        } catch (Exception e) {
            throw new SQLiteException("ERRO: Inserir Cliente" + e.getMessage());
        }
    }
    public Cliente consultarUsuario(String usuario, String senha) {
        Cliente cliente = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] colunas = {COLUNA_ID, COLUNA_NOME, COLUNA_CPF, COLUNA_USUARIO, COLUNA_SENHA};
        String selecao = COLUNA_USUARIO + " = ? AND " + COLUNA_SENHA + " = ?";
        String[] selecaoArgs = {usuario, senha};
        try {
            Cursor cursor = db.query(TABELA_CLIENTE,
                    colunas,
                    selecao,
                    selecaoArgs,
                    null,
                    null,
                    null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    cliente = new Cliente();
                    cliente.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                    cliente.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                    cliente.setCpf(cursor.getString(cursor.getColumnIndex(COLUNA_CPF)));
                    cliente.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USUARIO)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_SENHA)));
                    cursor.close();
                }
            }
            return cliente;

        } catch (Exception e) {
            throw new SQLiteException("ERRO: Consultar Usuário " + " : " + e.getMessage());

        }
    }

    public Cliente consultarCliente(String cpf) {
        Cliente cliente = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] colunas = {COLUNA_NOME, COLUNA_CPF, COLUNA_USUARIO, COLUNA_SENHA};
        String selecao = COLUNA_CPF + " = ?";
        String[] selecaoArgs = {cpf};
        try {
            Cursor cursor = db.query(TABELA_CLIENTE,
                    colunas,
                    selecao,
                    selecaoArgs,
                    null,
                    null,
                    null);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    cliente = new Cliente();
                    cliente.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                    cliente.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                    cliente.setCpf(cursor.getString(cursor.getColumnIndex(COLUNA_CPF)));
                    cliente.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USUARIO)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_SENHA)));
                    cursor.close();
                }
            }
            return cliente;

        } catch (Exception e) {
            throw new SQLiteException("ERRO: Consultar Cliente " + " : " + e.getMessage());

        }
    }

    public boolean excluirCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] colunas = new String[]{Integer.toString(id)};
        String selecao = COLUNA_ID + " = ?";
        try {
            db.delete(TABELA_CLIENTE, selecao, colunas);
            return true;
        } catch (Exception e) {
            throw new SQLiteException("ERRO: Exclusão " + " : " + e.getMessage());
        }
    }

    public boolean alterarCliente(int id, String nome, String cpf, String usuario, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String colunas[] = new String[]{Integer.toString(id)};
        String selecao = COLUNA_ID + " = ?";
        try {
            contentValues.put(COLUNA_NOME, nome);
            contentValues.put(COLUNA_CPF, cpf);
            contentValues.put(COLUNA_USUARIO, usuario);
            contentValues.put(COLUNA_SENHA, senha);
            db.update(TABELA_CLIENTE, contentValues, selecao, colunas);
            return true;

        } catch (Exception e) {
            throw new SQLiteException("ERRO: Atualização " + " : " + e.getMessage());
        }
    }

}
