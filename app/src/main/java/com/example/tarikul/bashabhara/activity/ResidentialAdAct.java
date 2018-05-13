package com.example.tarikul.bashabhara.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarikul.bashabhara.R;
import com.example.tarikul.bashabhara.model.ResidentialModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ResidentialAdAct extends AppCompatActivity {
    DatabaseReference databaseReference;
    ResidentialModel residentialModel;
    EditText edtTitle,edtAddress,edtRent,edtSize,edtDescription,edtPhone;
    RadioGroup rgDrawingRoom,rgDinngRoom,rgCarparking,rgLift,rgGas,rgGenerator,rgElectricBill;
    Spinner spinDivision,spinDistrict,spinThana,spinArea;
    RadioButton selectedDrRoom,selectedDinRoom,selectedcarP
            ,selectedLift,selectedGas,selectedGenerator,selectedElectricB;
    CheckBox cbFamilyHouse,cbBachelorMess,cbFemaileMess,cbSublet;
    Button btnTakePhoto,btnSaveData;
    TextView txvPhotoName;

    //initialize
    String title = "";
    String division = "";
    String district = "";
    String thana ="";
    String area = "";
    String address = "";
    String rent ="";
    String size = "";
    String description = "";
    String drawRoom = "";
    String dinningRoom = "";
    String carParking = "";
    String lift = "";
    String gas = "";
    String generator = "";
    String electBill = "";

    String familyHouse = "";
    String bachelorMess = "";
    String femaleMess = "";
    String sublet = "";
    String phone = "";
    String imagepath = "";
    String category = "";


    //Thana
    String[] thanaItemDha;
    String[] thanaItemKhu;
    //district
    String[] disDhaDiv;
    String[] disKhulDiv;
    //area
    String[] thanaMirpurArea;

    public static ArrayList<String> categoryList = new ArrayList<String>();
    private static String LIST_SEPARATOR = ":";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_ad);
        initViews();



    }

    public void initViews(){
        residentialModel = new ResidentialModel();
        databaseReference = FirebaseDatabase.getInstance().getReference("basha");

        edtTitle = (EditText)findViewById(R.id.edt_title);
        edtAddress = (EditText)findViewById(R.id.edt_address);
        edtRent = (EditText)findViewById(R.id.edt_rent);
        edtSize = (EditText)findViewById(R.id.edt_size);
        edtDescription = (EditText)findViewById(R.id.edt_descrip);
        edtPhone = (EditText)findViewById(R.id.edt_phone);

        spinDivision = (Spinner)findViewById(R.id.spin_division);
        spinDistrict = (Spinner)findViewById(R.id.spin_district);
        spinThana  = (Spinner)findViewById(R.id.spin_thana);
        spinArea   = (Spinner)findViewById(R.id.spin_area);

        rgDrawingRoom = (RadioGroup)findViewById(R.id.rg_drawing_room);
        rgDinngRoom = (RadioGroup)findViewById(R.id.rg_dinning_room);
        rgCarparking = (RadioGroup)findViewById(R.id.rg_car_parking);
        rgLift = (RadioGroup)findViewById(R.id.rg_lift);
        rgGas = (RadioGroup)findViewById(R.id.rg_gas);
        rgGenerator = (RadioGroup)findViewById(R.id.rg_generator);
        rgElectricBill = (RadioGroup)findViewById(R.id.rg_electric_bill);


        //rbDrawRoomN =(RadioButton)findViewById(R.id.btn_drawing_no);

        //rbDinRoomN =(RadioButton)findViewById(R.id.btn_dinning_no);
//        rbCarParkingY =(RadioButton)findViewById(R.id.btn_car_yes);
//        rbCarParkingN =(RadioButton)findViewById(R.id.btn_car_no);
//
//        rbLiftY =(RadioButton)findViewById(R.id.btn_lift_yes);
//        rbLiftN =(RadioButton)findViewById(R.id.btn_lift_no);
//        rbGasY =(RadioButton)findViewById(R.id.btn_gas_yes);
//        rbGasN =(RadioButton)findViewById(R.id.btn_gas_no);
//        rbGeneratorY =(RadioButton)findViewById(R.id.btn_generator_yes);
//        rbGeneratorN =(RadioButton)findViewById(R.id.btn_generator_no);
//        rbElectEx =(RadioButton)findViewById(R.id.btn_excluding);
//        rbElectIn =(RadioButton)findViewById(R.id.btn_including);

        cbFamilyHouse =(CheckBox)findViewById(R.id.chk_family_house);
        cbBachelorMess =(CheckBox)findViewById(R.id.chk_bachelor_mess);
        cbFemaileMess =(CheckBox)findViewById(R.id.chk_female_mess);
        cbSublet =(CheckBox)findViewById(R.id.chk_sublet);

        btnTakePhoto = (Button)findViewById(R.id.btn_take_photo);
        btnSaveData = (Button)findViewById(R.id.btn_save);
        txvPhotoName = (TextView) findViewById(R.id.txv_pic_info);

        thanaItemDha = getResources().getStringArray(R.array.thana_dhaka);
        thanaItemKhu = getResources().getStringArray(R.array.thana_khulna);

        disDhaDiv = getResources().getStringArray(R.array.dis_dhaka_div);
        disKhulDiv = getResources().getStringArray(R.array.dis_khulna_div);

        thanaMirpurArea = getResources().getStringArray(R.array.area_mirpur);

        spinDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String division = spinDivision.getSelectedItem().toString();
                if (division.equals("Dhaka")){
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, disDhaDiv);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                    spinDistrict.setAdapter(spinnerArrayAdapter);

                }
                if (division.equals("Khulna")){
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, disKhulDiv);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                    spinDistrict.setAdapter(spinnerArrayAdapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String district=spinDistrict.getSelectedItem().toString();
                if (district.equals("Dhaka")){
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, thanaItemDha);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                    spinThana.setAdapter(spinnerArrayAdapter);

                }
                if (district.equals("Khulna")){
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, thanaItemKhu);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                    spinThana.setAdapter(spinnerArrayAdapter);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        spinThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String thana = spinThana.getSelectedItem().toString();
                if (thana.equals("Mirpur Model")){
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, thanaMirpurArea);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
                    spinArea.setAdapter(spinnerArrayAdapter);

                }
//                if (district.equals("Khulna")){
//                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ResidentialAdAct.this, android.R.layout.simple_spinner_item, thanaItemKhu);
//                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
//                    spinThana.setAdapter(spinnerArrayAdapter);
//
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });





        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    title = edtTitle.getText().toString();
                    division = spinDivision.getSelectedItem().toString();
                    district = spinDistrict.getSelectedItem().toString();
                    thana = spinThana.getSelectedItem().toString();
                    area = spinArea.getSelectedItem().toString();
                    address = edtAddress.getText().toString();
                    rent = edtRent.getText().toString();
                    size = edtSize.getText().toString();
                    description = edtDescription.getText().toString();
                    selectedDrRoom =(RadioButton)findViewById(rgDrawingRoom.getCheckedRadioButtonId());
                    drawRoom = selectedDrRoom.getText().toString();
                    selectedDinRoom =(RadioButton)findViewById(rgDinngRoom.getCheckedRadioButtonId());
                    dinningRoom = selectedDinRoom.getText().toString();
                    selectedcarP = (RadioButton)findViewById(rgCarparking.getCheckedRadioButtonId());
                    carParking = selectedcarP.getText().toString();
                    selectedLift = (RadioButton)findViewById(rgLift.getCheckedRadioButtonId());
                    lift = selectedLift.getText().toString();
                    selectedGas = (RadioButton)findViewById(rgGas.getCheckedRadioButtonId());
                    gas = selectedGas.getText().toString();
                    selectedGenerator = (RadioButton)findViewById(rgGenerator.getCheckedRadioButtonId());
                    generator = selectedGenerator.getText().toString();
                    selectedElectricB = (RadioButton)findViewById(rgElectricBill.getCheckedRadioButtonId());
                    electBill = selectedElectricB.getText().toString();


                if (cbFamilyHouse.isChecked()) {
                    familyHouse = cbFamilyHouse.getText().toString();
                        categoryList.add(familyHouse);

                }


                if (cbBachelorMess.isChecked()) {
                    bachelorMess = cbBachelorMess.getText().toString();
                    categoryList.add(bachelorMess);

                }
                if (cbFemaileMess.isChecked()) {
                    femaleMess = cbFemaileMess.getText().toString();
                    categoryList.add(femaleMess);

                }
                if (cbSublet.isChecked()) {
                    sublet = cbSublet.getText().toString();
                    categoryList.add(sublet);

                }
                if(categoryList.size()>0){
                    category = convertListToString(categoryList);
                }

                phone = edtPhone.getText().toString();
                imagepath = "abc.jpg";

                sendToServer(title,division,district,thana,
                        area,address,rent,size,description,drawRoom,dinningRoom,
                        carParking,lift,gas,generator,electBill,category,phone,imagepath);

            }
        });


    }

    public void sendToServer(String title,String division,String district,String thana,String area
            ,String address,String rent,String size,String description,
                             String drawRoom,String dinningRoom,
                             String carParking,String lift,String gas,
                             String generator,String electBill,String category,String phone,String housePic)
    {
        residentialModel.setTitle(title);
        residentialModel.setDivision(division);
        residentialModel.setDistrict(district);
        residentialModel.setThana(thana);
        residentialModel.setArea(area);
        residentialModel.setAddress(address);
        residentialModel.setRent(rent);
        residentialModel.setSize(size);
        residentialModel.setDescription(description);
        residentialModel.setDrawingRoom(drawRoom);
        residentialModel.setDinningRoom(dinningRoom);
        residentialModel.setCarParking(carParking);
        residentialModel.setLift(lift);
        residentialModel.setGas(gas);
        residentialModel.setGenerator(generator);
        residentialModel.setElectricBill(electBill);
        residentialModel.setCategory(category);
        residentialModel.setPhone(phone);
        residentialModel.setImagePath(housePic);


        try {
            databaseReference.child(residentialModel.getPhone()).setValue(residentialModel);
            Toast.makeText(this, "Successfully send to server", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Some Issue (Check your Internet connection and try again)", Toast.LENGTH_SHORT).show();
        }

    }


    public static String convertListToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }
        try {
            // Remove last separator
            int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
            stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);
        }catch (Exception e){
            e.getStackTrace();

        }


        return stringBuffer.toString();
    }


}
