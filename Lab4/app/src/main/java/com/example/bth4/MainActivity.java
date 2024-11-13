package com.example.bth4;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.filament.View;

public class MainActivity extends AppCompatActivity {

    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode, btnBlinkXml, btnBlinkCode,
            btnZoomInXml, btnZoomInCode, btnZoomOutXml, btnZoomOutCode, btnRotateXml, btnRotateCode,
            btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode, btnBounceXml, btnBounceCode,
            btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();

        // Use animation from XML

        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);

        // Use animation from CODE.

        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());

        // The event happened when the UIT logo was clicked.

        ivUitLogo = findViewById(R.id.iv_uit_logo);
        ivUitLogo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void findViewsByIds() {
        ivUitLogo = findViewById(R.id.iv_uit_logo);
        btnFadeInXml = findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = findViewById(R.id.btn_blink_xml);
        btnBlinkCode = findViewById(R.id.btn_blink_code);
        btnZoomInXml = findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = findViewById(R.id.btn_rotate_xml);
        btnRotateCode = findViewById(R.id.btn_rotate_code);
        btnMoveXml = findViewById(R.id.btn_move_xml);
        btnMoveCode = findViewById(R.id.btn_move_code);
        btnSlideUpXml = findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = findViewById(R.id.btn_slide_up_code);
        btnBounceXml = findViewById(R.id.btn_bounce_xml);
        btnBounceCode = findViewById(R.id.btn_bounce_code);
        btnCombineXml = findViewById(R.id.btn_combine_xml);
        btnCombineCode = findViewById(R.id.btn_combine_code);
    }
    private void initVariables() {
        listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation start logic
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeat logic
            }
        };
    }
    private void handleClickAnimationXml(Button btn, int animId) {
        btn.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, animId);
            anim.setAnimationListener(listener);
            ivUitLogo.startAnimation(anim);
        });
    }
    private void handleClickAnimationCode(Button btn, final Animation animation) {
        btn.setOnClickListener(v -> {
            animation.setAnimationListener(listener);
            ivUitLogo.startAnimation(animation);
        });
    }
    // Animation by Code
    private Animation initFadeOutAnimation() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        anim.setAnimationListener(listener);
        return anim;
    }
    private Animation initFadeInAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        anim.setAnimationListener(listener);
        return anim;
    }
    private Animation initBlinkAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        return anim;
    }
    private Animation initZoomInAnimation() {
        ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }
    private Animation initZoomOutAnimation() {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }
    private Animation initRotateAnimation() {
        RotateAnimation anim = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }
    private Animation initMoveAnimation() {
        TranslateAnimation anim = new TranslateAnimation(0, 200, 0, 0); // Di chuyển từ vị trí ban đầu sang phải 200px
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }
    private Animation initSlideUpAnimation() {
        TranslateAnimation anim = new TranslateAnimation(0, 0, 200, 0); // Trượt từ dưới lên trên (200px)
        anim.setDuration(1000);
        anim.setFillAfter(true);
        return anim;
    }
    private Animation initBounceAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        scaleAnimation.setDuration(500);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setFillAfter(true);

        return scaleAnimation;
    }

    private Animation initCombineAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 3.0f,
                1.0f, 3.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(4000);

        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);

        return animationSet;
    }






}
