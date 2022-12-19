package com.fastinventory.fastinventoryfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fastinventory.fastinventoryfi.modelo.Main;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText codProducto;
    private EditText nomProducto;
    private EditText preciProducto;
    private EditText cantProducto;
    private EditText fechProducto;
    private ListView listViewProductos;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Main> listProducto=new ArrayList<Main>();
    ArrayAdapter<Main> arrayAdapterProducto;
    Main productoSelected;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codProducto = findViewById(R.id.txtCodigoProd);
        nomProducto = findViewById(R.id.txtNomProd);
        preciProducto = findViewById(R.id.txtPreciProd);
        cantProducto  = findViewById(R.id.txtCantProd);
        fechProducto = findViewById(R.id.txtFechProd);
        listViewProductos = findViewById(R.id.ListViewProductos);
        inicializarFirebase();
        listarDatos();
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productoSelected = (Main) parent.getItemAtPosition(position);
                codProducto.setText(productoSelected.getCodProducto());
                nomProducto.setText(productoSelected.getNomProducto());
                preciProducto.setText(productoSelected.getPreciProducto());
                cantProducto.setText(productoSelected.getCantProducto());
                fechProducto.setText(productoSelected.getFechProducto());

            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String codigo = codProducto.getText().toString();
        String  nombre = nomProducto.getText().toString();
        String precio = preciProducto.getText().toString();
        String cantidad = cantProducto.getText().toString();
        String fecha = fechProducto.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_guardar:
                if (codigo.isEmpty()||nombre.isEmpty()||precio.isEmpty()||cantidad.isEmpty()||fecha.isEmpty()){
                    validacion();
                }
                else {
                    Main main = new Main();
                    main.setCodProducto(codigo);
                    main.setNomProducto(nombre);
                    main.setPreciProducto(precio);
                    main.setCantProducto(cantidad);
                    main.setFechProducto(fecha);
                    databaseReference.child("producto").child(main.getCodProducto()).setValue(main);

                    Toast.makeText(this, "Guardo el producto exitosamente", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                    break;
                }

            case R.id.icon_actualizar:
                Main main = new Main();
                main.setCodProducto(productoSelected.getCodProducto());
                main.setNomProducto(nomProducto.getText().toString().trim());
                main.setPreciProducto(preciProducto.getText().toString().trim());
                main.setCantProducto(cantProducto.getText().toString().trim());
                main.setFechProducto(fechProducto.getText().toString().trim());
                databaseReference.child("producto").child(main.getCodProducto()).setValue(main);
                Toast.makeText(this, "Actualizo informacion de producto", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_eliminar:
                main = new Main();
                main.setCodProducto(productoSelected.getCodProducto());
                databaseReference.child("producto").child(main.getCodProducto()).removeValue();

                Toast.makeText(this, "Elimino producto exitosamente", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_inicio:
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
        return  true;

    }
    private  void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Toast.makeText(this, "Inicializand base de datos", Toast.LENGTH_SHORT).show();
    }
    public void limpiarCajas(){
        codProducto.setText("");
        nomProducto.setText("");
        preciProducto.setText("");
        cantProducto.setText("");
        fechProducto.setText("");



    }
    public  void validacion(){
        String codigo = codProducto.getText().toString();
        String  nombre = nomProducto.getText().toString();
        String precio = preciProducto.getText().toString();
        String cantidad = cantProducto.getText().toString();
        String fecha = fechProducto.getText().toString();
        if (codigo.isEmpty()){
            codProducto.setError("requerido");
        }
        else if (nombre.isEmpty()){
            nomProducto.setError("requerido");

        }
        else if (precio.isEmpty()){
            preciProducto.setError("requerido");
        }
        else if (cantidad.isEmpty()){
            cantProducto.setError("requerido");
        }
        else if (fecha.isEmpty()){
            fechProducto.setError("requerido");
        }

    }
    private void listarDatos(){

        databaseReference.child("producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProducto.clear();
                for(DataSnapshot objSnapshot: snapshot.getChildren()){
                    Main main = objSnapshot.getValue(Main.class);
                    listProducto.add(main);
                    arrayAdapterProducto = new ArrayAdapter<Main>(MainActivity.this, android.R.layout.simple_list_item_1,listProducto);
                    listViewProductos.setAdapter(arrayAdapterProducto);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}