package com.example.mutlubievcase.utils

import com.example.mutlubievcase.R
import com.example.mutlubievcase.models.BasicViewPagerData
import com.example.mutlubievcase.models.CampaignViewPagerData

object Constants {

    val threeStepList= mutableListOf(
        BasicViewPagerData("Online Rezervasyon","Websitemizden veya mobil uygulamamızdan istediğin gün ve saat için rezervasyon yap.",
            R.drawable.ic_physics),
        BasicViewPagerData("Sigortalı Hizmet","Mutlubiev sistemine kayıtlı profesyoneller evinde bulunduğu sürece tüm yasal gerelilikleri senin adına sağlıyoruz. Güvenilir profesyoneller istediğin saatte kapında olsun.",
            R.drawable.ic_clean),
        BasicViewPagerData("Keyfini Çıkar","Evinin keyfini çıkarmak sana kalsın!",
            R.drawable.ic_clock)
    )

    val whyMutlubievList= mutableListOf(
        BasicViewPagerData("Profesyonel Hizmet","Tüm profesyonellerle yüz yüze görüşüp düzenli eğitimler veriyoruz. Ayrıca Mutlubiev sitemine kayıtlı profesyoneller evinde bulunduğu sürece tüm yasal gerekliliklerin senin adına sağlıyoruz.",
            R.drawable.ic_physics),
        BasicViewPagerData("Güvenilir Profesyoneller","Profesyonellerin adli kayıtlarını kontrol ediyoruz.",
            R.drawable.ic_clean),
        BasicViewPagerData("Malzeme Derdi Yok!","Temizlik malzemelerini dert etmene gerek yok! Eğer istersen, profesyonellerimiz temizlik malzemelerini yanlarında getiriyor.",
            R.drawable.ic_clock),
        BasicViewPagerData("Sigorta Güvencesi","Hizmet süresince evinizi AXA ile sigortalıyoruz.",
            R.drawable.ic_clock)
    )

    val campaignList= mutableListOf(
        CampaignViewPagerData(R.drawable.main_image,"Ayrıcalıklı Ev Temizliğiniz Mutlubiev'den!"),
        CampaignViewPagerData(R.drawable.main_image,"Maximum Kart sahipleri Mutlubiev'de ayrıcalıklı!"),
        CampaignViewPagerData(R.drawable.main_image,"Ayrıcalıklı Ev Temizliğiniz Mutlubiev'den!")
    )
}