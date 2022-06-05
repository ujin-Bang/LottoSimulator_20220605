package com.start.lottosimulator_20220605

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.start.lottosimulator_20220605.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val mWinLottoNumArr = ArrayList<Int>()
    var mBonusNum = 0

//    내가 쓴 금액? 합산 변수
    var mUsedMoney = 0L // Long타입(긴 숫자 표현) 명시

//    당첨 금액? 합산 변수
    var mEarnedMoney = 0L

    lateinit var mLottoNumTxtList : ArrayList<TextView>
    val mMyLottoNumArr = arrayListOf(5,17,26,30,36,42)

//    등수별 당첨 횟수 목록
    val mRankCountList = arrayListOf(0,0,0,0,0,0)

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
            makeBonusNum()

//            등수 판정
            checkLottoRank()
        }

    }

    fun checkLottoRank(){

//        1천원 사용으로 간주.
        mUsedMoney += 1000


//        내 숫자 6개가 -> 당첨번호 6개중 몇개나 맞췄는지?

        var correctCount = 0

        for ( myNum in mMyLottoNumArr ){

//            맟췃는가? -> myNum이 당첨번호에 들어있는가?
            if( mWinLottoNumArr.contains(myNum)){
//                맞춪 갯수 증가
                correctCount++
            }
        }

        Log.d("맞춘 갯수", "${correctCount}개 맞춤")

//        등수 판단(if대신 when활용)
        when(correctCount){
            6 -> {
                Log.d("등수","1등입니다.")
                mEarnedMoney += 5000000000

                mRankCountList[0]++

                binding.txtRankCount1.text = "${mRankCountList[0]}회"
            }
            5 -> {

//                보너스번호 검사 => 보너스 번호가 내 번호안에 있는가?

                if(mMyLottoNumArr.contains( mBonusNum)){
                    Log.d("등수","2등")

                    mEarnedMoney += 50000000

                    binding.txtRankCount2.text = "${mRankCountList[1]}회"
                }
                else {
                    Log.d("등수","3등")

                    mEarnedMoney += 2000000
                    binding.txtRankCount3.text = "${mRankCountList[2]}회"

                }
            }
            4 -> {
                Log.d("등수", "4등")
                mEarnedMoney += 50000

                binding.txtRankCount4.text = "${mRankCountList[3]}회"

            }
            3 -> {
                Log.d("등수","5등")
                mUsedMoney -= 5000
                binding.txtRankCount5.text = "${mRankCountList[4]}회"

            }
            else -> {
                Log.d("등수","꽝")
                binding.txtRankCount6.text = "${mRankCountList[5]}회"

            }
        }

        binding.txtUsedMoney.text = "${NumberFormat.getInstance(Locale.KOREA).format(mUsedMoney)}원"
        binding.txtEarnedMoney.text = "${NumberFormat.getInstance(Locale.KOREA).format(mEarnedMoney)}원"

    }

    fun makeBonusNum(){

//        써도 되는 숫자가 나올때까지 무한반복
        while (true){

            val randomNum = (1..45).random()

            val isDuplOk = !mWinLottoNumArr.contains(randomNum)

            if(isDuplOk){
                mBonusNum = randomNum
                break
            }

        }

//        보너스번호 텍스트뷰에 반영
         binding.txtBonusNum.text = mBonusNum.toString()
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