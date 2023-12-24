# Merancang Aplikasi Android Pemrosesan Suara untuk Meringkas Materi

## Latar Belakang
Dalam era digital yang berkembang pesat dengan aliran informasi yang semakin cepat, Seseorang dapat dihujani oleh berlimpah informasi baru dari berbagai sumber. Berkat perkembangan teknologi, banyak orang sekarang dapat berbagi informasi dalam berbagai format dengan berbagai model komunikasi yang mereka inginkan(https://arxiv.org/pdf/2109.05199.pdf ). Meskipun terdapat berbagai cara untuk komunikasi dan mengekspresikan pemikiran dan perasaan kita, bicara tetap dianggap sebagai medium utama dalam berkomunikasi, yang tetap menjadi unsur terpenting dalam interaksi manusia (file:///C:/Users/User/Downloads/Speech_to_text_conversion_and_summarization_for_ef.pdf). banyaknya informasi yang diterima sesorang dapat menyebabkan di mana seseorang kesulitan dalam mengolah informasi dan menjadikannya tidak efisien (https://www.sciencedirect.com/science/article/pii/S030645732030933X#bib0011 ). perlu ada solusi untuk membantu pengguna merangkum dan memahami materi dengan lebih efisien. 

## Branding
Pada tahap ini kita mengeksplorasi branding dari produk UbiCom yang dibuat. Contoh:
- Merk: SummIT
- Inspirasi merk: SummIT yang memiliki kepanjangan Summarize it yang berarti Merangkumnya
- Tagline: Catat Materi lebih mudah
- Campaign: catatan tetap lengkap walau tidak fokus
- Target user:
  - Usia 12+
  - Pelajar, Pekerja
- User experience theme:
  - Mudah dikonfigurasi dan digunakan

## User Story
|Sebagai|Saya ingin bisa|Sehingga|Prioritas
|---|---|---|---|
|Sistem|Merekam audio yang jelas dan tahan lama|Bisa mendapatkan audio yang berkualitas|⭐⭐⭐⭐⭐|
|Sistem Smartphone|Menjadikan teks dari sebuah suara|Bisa menjadi sebuah teks yang siap dibuat kesimpulan|⭐⭐⭐⭐⭐|
|Sistem cloud|menyimpulkan teks |Bisa menjadi input kalkulasi respon yang sesuai untuk pengguna|⭐⭐⭐⭐⭐|
|Sistem cloud|menyimpan data yang sebelum dan sesudah disimpulkan |Data yang disimpan bisa menjadi dataset dan digunakan kembali untuk mengembangkan model|⭐⭐⭐⭐|
|Sistem cloud|mengirim hasil data|Bisa menjadi input kalkulasi respon yang sesuai untuk pengguna|⭐⭐⭐⭐⭐|
|Sistem Smartphone|Menampilkan hasil dengn mudah dibaca|Pengguna bisa lebih terbantu dengan ui yang baik|⭐⭐⭐⭐⭐|


## Metode dan Algoritma
- Sensor:
  - Speech recognition: SpeechRecognizer adalah kemampuan untuk mengubah ucapan manusia menjadi teks. Ini adalah teknologi yang memungkinkan perangkat Android untuk mendengarkan suara pengguna dan kemudian menerjemahkannya menjadi teks yang dapat dipahami oleh aplikasi atau sistem.
- Mobile software development
- Cloud server
- Model ai summarize : hugging face model thonyyy/pegasus_indonesian_base-finetune
- flutter_deep_speech plugin Flutter untuk pengenalan suara dan transkripsi menggunakan DeepSpeech. Ini adalah plugin yang dapat digunakan untuk mentranskripsikan audio dari berbagai sumber, termasuk mikrofon, file, dan audio streaming.
- flutter_tts plugin Flutter yang menyediakan fungsi Text-to-Speech (TTS) untuk mengonversi teks menjadi suara. Plugin ini menawarkan berbagai opsi konfigurasi, termasuk pengaturan kecepatan pembacaan, 

## Struktur Data
![erd](https://github.com/munazirdzuana/SummIt/assets/78277922/5c9337b6-5b3f-4c43-ae20-7766f555d8a4)

## Arsitektur Sistem
![whehe (6)](https://github.com/munazirdzuana/SummIt/assets/78277922/0f2b66fd-d5e7-4054-85a7-7e345c729fac)

## Deskripsi Teknologi
- Mesin komputasi
  - Cloud server: AWS EC2 layanan komputasi awan yang diberikan oleh Amazon Web Services (AWS), memberikan kapasitas komputasi virtual yang dapat diubah-ubah seiring kebutuhan
  - Smart phone: Android dan IOS
- Software development
  - Mobile development: Flutter  Dikembangkan oleh Google, Flutter menggunakan bahasa pemrograman Dart dan menawarkan pendekatan "write once, run anywhere," yang memungkinkan pengembang untuk membuat kode sumber tunggal yang kaya fitur dan responsif dengan desain yang konsisten di berbagai platform seperti iOS, Android, dan web
  - Backend developer: FastAPI framework web Python yang dirancang untuk memungkinkan pengembangan API (Application Programming Interface) dengan cepat, efisien, dan mudah dipahami. 
- Sensor
  - MIC Smartphone 
- Responder
  - Speaker Smartphone
  volume, dan bahasa suara.
## User Experience (UX) Design
![Screenshot 2023-10-30 090544](https://github.com/munazirdzuana/SummIt/assets/78277922/e38aaa3f-24f7-4b66-b08b-9f4dea48d720)

