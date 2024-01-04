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

## Arsitektur Sistem
![12 drawio](https://github.com/munazirdzuana/SummItt/assets/78277922/38dcc060-37c2-4262-ac9e-c9ef8ebb12e0)

## Deskripsi Teknologi
- Mesin komputasi
  - Cloud server: AWS EC2 layanan komputasi awan yang diberikan oleh Amazon Web Services (AWS), memberikan kapasitas komputasi virtual yang dapat diubah-ubah seiring kebutuhan
  - Smart phone: Android 
- Software development
  - Mobile development: Native Android=
- Sensor
  - MIC Smartphone 
- Responder
  - Speaker Smartphone
  volume, dan bahasa suara.
## User Experience (UX) Design
![WhatsApp Image 2023-12-19 at 20 13 34_30e6b503](https://github.com/munazirdzuana/SummItt/assets/78277922/4c37ca1f-7c5e-4d7f-8a09-acdea22a0383)

