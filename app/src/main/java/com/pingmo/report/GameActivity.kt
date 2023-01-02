package com.pingmo.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.pingmo.report.databinding.ActivityGameBinding
import java.util.*

class GameActivity : AppCompatActivity() {
    private var score: Long = 0

    private val arr = arrayOf(Place("만리장성", "장성에 가보지 않은 자, 사내 대장부라 할 수 없다. - 마오쩌동", img = R.drawable.img1),
        Place("천안문", "하늘의 평안한 문이라는 뜻이며 한문으로는 명을 따르고 하늘을 섬겨 나라를 평안하게 하고 백성을 다스린다는 뜻을 갖고 있습니다", img = R.drawable.img2),
        Place("자금성", "세계 최대 규모의 궁궐 입니다", img = R.drawable.img3),
        Place("병마용", "진시황릉에서 1km가량 떨어진 유적지로 흙을 구워 만든 수 많은 병사,말 등 모형이 있는 갱도 입니다", img = R.drawable.img4),
        Place("이화원", "바뀌기 이전의 이름은 청의원이라고 합니다", img = R.drawable.img5),
        Place("포탈라 궁", "부처가 산다는 것으로 여겨진 티베트의 포탈라카 산의 이름에서 따온 것이라고 합니다", img = R.drawable.img6),
        Place("상하이", "중국에서 인구가 가장 많은 도시입니다", img = R.drawable.img7),
        Place("와이탄", "상하이의 주요 건물과 야경을 가장 잘 조명할 수 있는 곳입니다.", img = R.drawable.img8),
        Place("천단", "명청시대 중국에서 군주가 제천의식을 행하던 도교 제단 입니다", img = R.drawable.img9),
        Place("황산", "사진을 자세히 보면 원숭이가 보인다고 합니다", img = R.drawable.img10)
    )

    lateinit var btns: Array<Button>
    lateinit var res: Place

    private lateinit var mBinding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGameBinding.inflate(layoutInflater).apply {
            btns = arrayOf(btn1, btn2, btn3, btn4)
            changeQuestion(imgGame)

            txtScore.text = "점수 : ${score}"
            btnHint.setOnClickListener {
                HintDialog(this@GameActivity, res.detail).show()
            }

            for(btn in btns) {
                btn.setOnClickListener {
                    if(btn.text == res.name) {
                        ResultDialog(this@GameActivity, "정답입니다\n축하드려요.").show()
                        score += 100
                    }else {
                        ResultDialog(this@GameActivity, "아쉽지만, 틀렸습니다.\n정답은 '${res.name}' 입니다.").show()
                        score = 0
                    }
                    txtScore.text = "점수 : ${score}"
                    changeQuestion(imgGame)
                }
            }
        }
        setContentView(mBinding.root)
    }

    private fun changeQuestion(imgGame: ImageView) {
        val ran = Random().nextInt(arr.size)
        res = arr[ran]

        val set = mutableSetOf<Int>()
        set += ran

        while(set.size < 4) {
            set += arr.indices.random()
        }
        val list = set.shuffled()
        for(i in btns.indices) {
            btns[i].text = arr[list[i]].name
        }
        imgGame.setImageResource(res.img)
    }
}