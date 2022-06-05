package com.start.lottosimulator_20220605

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.start.lottosimulator_20220605.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents(){

        binding.btnBuyLotto.setOnClickListener {

//            로또번호 6개 생성
            makeLottoNumbers()

//            보너스 번호 생성
        }

    }

    fun makeLottoNumbers(){

//        6개의 당첨 번호 => 반복횟수 명확 => for문
        for(i in 0 until 6){

            Log.d("숫자확인",i.toString())
        }

    }

    fun setValues(){

    }
}