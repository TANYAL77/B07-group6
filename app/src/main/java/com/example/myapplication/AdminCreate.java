package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentAdminCreateBinding;
//import com.example.myapplication.databinding.FragmentAdminEditBinding;
import com.example.myapplication.databinding.AdminFragmentBinding;

import java.util.ArrayList;

public class AdminCreate extends Fragment{
/* autogenerated stuff

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_edit, container, false);
    }
 */

    private FragmentAdminCreateBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        System.out.println("In AdminCreate");
        binding = FragmentAdminCreateBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("ON ATTACH");
        /*
        try {
            mListener = (ExampleFragmentCallbackInterface ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ExampleFragmentCallbackInterface ");
        }
        */
    }

    Button SaveInput;
    EditText GetInput;
    String name;
    String code;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AdminHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminCreate.this)
                        .navigate(R.id.action_AdminCreate_to_AdminFragment);
            }
        });

        SaveInput = getActivity().findViewById(R.id.submit);
        GetInput = getActivity().findViewById(R.id.CreateNameInput);
        boolean sessionsOffered[] = {false, false, false};

        binding.FallSessionChip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(sessionsOffered[0]==false) sessionsOffered[0] = true;
                else sessionsOffered[0] = false;
            }
        });
        binding.WinterSessionChip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(sessionsOffered[1]==false) sessionsOffered[1] = true;
                else sessionsOffered[1] = false;
            }
        });
        binding.SummerSessionChip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(sessionsOffered[2]==false) sessionsOffered[2] = true;
                else sessionsOffered[2] = false;
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("REA");
                //Course c = new Course();
                System.out.println("IS COURSE NULL: "+Course.courseList == null);
                System.out.println("Before call:"+Course.courseList.size());
                name = GetInput.getText().toString();
                GetInput = getActivity().findViewById(R.id.CreateCodeInput);
                code = GetInput.getText().toString();

                if(Course.doesCourseCodeExist(code)) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            ("Code \""+code + "\" is already in use. Please try a different code."),
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    Course createdCourse = new Course(name, code, sessionsOffered, new ArrayList<Course>());
                    Course.courseList.add(createdCourse);
                    /**
                    System.out.println("Code of most recent course: " + Course.courseList.get(Course.courseList.size()-1).courseCode);
                    System.out.println("courseList.get(3) (fall)" + Course.courseList.get(3).sessions[0]);
                    System.out.println("courseList.get(3) (winter)" + Course.courseList.get(3).sessions[1]);
                    System.out.println("courseList.get(3) (summer)" + Course.courseList.get(3).sessions[2]);
                     **/
                    Toast.makeText(getActivity().getApplicationContext(),
                            ("Course \"" + code + "\" has been successfully created!"),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

/*
    public void store_view(){
        EditText createNewCourse = getActivity().findViewById(R.id.CreateNameInput);
        System.out.println(createNewCourse.getText().toString());
    }

 */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}