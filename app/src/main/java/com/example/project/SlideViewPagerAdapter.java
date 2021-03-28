package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);
        ImageView logo = view.findViewById(R.id.Iv_onboard_concern);
        ImageView sym1 = view.findViewById(R.id.Iv_onboard_sym1);
        ImageView sym2 = view.findViewById(R.id.Iv_onboard_sym2);
        ImageView sym3 = view.findViewById(R.id.Iv_onboard_sym3);
        TextView title = view.findViewById(R.id.tv_onboard_concern);
        TextView desc = view.findViewById(R.id.tv_onboard_concern_desc);
        ImageView back = view.findViewById(R.id.back);
        ImageView forward = view.findViewById(R.id.forward);
        Button getstarted = view.findViewById(R.id.get_started);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide_activity.viewPager.setCurrentItem(position+1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide_activity.viewPager.setCurrentItem(position-1);

            }
        });
        switch (position){
            case 0:
                logo.setImageResource(R.drawable.concerned_about_disease);
                sym1.setImageResource(R.drawable.selected);
                sym2.setImageResource(R.drawable.unselected);
                sym3.setImageResource(R.drawable.unselected);
                back.setVisibility(View.GONE);
                forward.setVisibility(View.VISIBLE);
                break;
            case 1:
                logo.setImageResource(R.drawable.book_doctor_image_2);
                sym1.setImageResource(R.drawable.unselected);
                sym2.setImageResource(R.drawable.selected);
                sym3.setImageResource(R.drawable.unselected);
                title.setText("Book a Doctor");
                desc.setText("Search and book appointment for a doctor based on area of expertise, years of experience and reviews by other patients! ");
                back.setVisibility(View.VISIBLE);
                forward.setVisibility(View.VISIBLE);

                break;
            case 2:
                logo.setImageResource(R.drawable.book_xray_image);
                sym1.setImageResource(R.drawable.unselected);
                sym2.setImageResource(R.drawable.unselected);
                sym3.setImageResource(R.drawable.selected);
                title.setText("Lab Appointment");
                desc.setText("Did your doctor suggest you to go for a x-ray? Get your report on the app and share it with your doctor in just a single click! ");
                forward.setVisibility(View.GONE);
                break;

        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}




