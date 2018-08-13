package sg.edu.rp.q.p13taskmanagerwear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnEdit, btnDelete;
    EditText etEditName, etEditDescription, etEditTime;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etEditName = findViewById(R.id.etEditName);
        etEditDescription = findViewById(R.id.etEditDescription);
        etEditTime = findViewById(R.id.etEditTime);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Task)i.getSerializableExtra("data");

        etEditName.setText(data.getName());
        etEditDescription.setText(data.getDescription());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setName(etEditName.getText().toString());
                data.setDescription(etEditDescription.getText().toString());
                int data1 = dbh.updateTask(data);
                Toast.makeText(EditActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteTask(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();

            }
        });
    }
}
