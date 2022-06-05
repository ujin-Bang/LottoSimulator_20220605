package com.start.lottosimulator_20220605

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.start.lottosimulator_20220605.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val mWinLottoNumArr = ArrayList<Int>()
    lateinit var mLottoNumTxtList : ArrayList<TextView>

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

//        기존에 번호가 있다면 전부 삭제.
        mWinLottoNumArr.clear()

//        6개의 당첨 번호 => 반복횟수 명확 => for문
        for(i in 0 until 6){

//            랜덤 숫자 추출 -> (제대로 된 숫자라면) 목록에 추가.
            while (true) {

                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복검사 : 당첨 숫자 목록에 내 숫자가 있는지?

                val isDupleOk = !mWinLottoNumArr.contains(randomNum)


                if(isDupleOk){

//                    숫자를 당첨 목록에 추가.
                    mWinLottoNumArr.add(randomNum)
                    break
                }

            }

        }

//        ArrayList의 sort기능 활용
        mWinLottoNumArr.sort()

//        당첨 번호 6개 확인

        for(i in 0 until 6){

//            텍스트뷰[i] = 당첨번호[i]
            mLottoNumTxtList[i].text = mWinLottoNumArr[i].toString()
        }

    }

    fun setValues(){

        mLottoNumTxtList = arrayListOf(binding.txtLottoNum1, binding.txtLottoNum2, binding.txtLottoNum3, binding.txtLottoNum4, binding.txtLottoNum5, binding.txtLottoNum6)

    }
}