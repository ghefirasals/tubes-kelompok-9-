
TiketyuApp adalah sebuah aplikasi berbasis Java GUI (Graphical User Interface) yang dirancang untuk mempermudah proses pemesanan tiket bioskop. Aplikasi ini menggunakan library bawaan Java, seperti Swing dan AWT, untuk menyediakan pengalaman pengguna yang interaktif dan intuitif tanpa memerlukan API eksternal.

Aplikasi ini menawarkan fitur-fitur utama, seperti pemilihan film, lokasi bioskop, waktu tayang, metode pembayaran, hingga pemilihan kursi secara interaktif. TiketyuApp juga mendukung penyimpanan hasil pemesanan tiket ke dalam file teks (hasil_tiket.txt).

Fitur Utama

Splash Screen:

Layar pembuka (splash screen) dengan logo Tiketyu dan pesan sambutan.
Menggunakan Timer untuk menampilkan splash screen selama 3 detik sebelum masuk ke aplikasi utama.
Tampilan yang estetis dengan penggunaan warna dan font yang menarik.

Pemilihan Film dan Lokasi:

Dropdown menu untuk memilih film yang tersedia.
Dropdown menu untuk memilih lokasi bioskop (misalnya Cinema XXI di berbagai lokasi).

Pemilihan Waktu Tayang dan Metode Pembayaran:

Dropdown menu untuk memilih jam tayang yang diinginkan.
Pilihan metode pembayaran, seperti Cash, Debit, dan PayLater.

Pemilihan Kursi:

Antarmuka interaktif untuk memilih kursi menggunakan tombol-tombol (5x5 grid) yang merepresentasikan barisan dan kolom kursi.
Kursi yang dipilih akan berubah warna dari hijau (tersedia) menjadi merah (terpilih).
Dukungan pembatalan pemilihan kursi dengan konfirmasi dari pengguna.

Konfirmasi dan Penyimpanan Tiket:

Hasil pemesanan tiket akan ditampilkan dalam bentuk pesan pop-up.
Tiket yang sudah dipesan akan disimpan ke dalam file teks hasil_tiket.txt, yang berisi detail seperti film, lokasi, waktu tayang, metode pembayaran, dan kursi yang dipilih.

Pembatalan Pesanan:

Tombol "Cancel Order" untuk mengatur ulang semua pilihan ke kondisi awal.
Konfirmasi pembatalan untuk memastikan pengguna tidak membatalkan secara tidak sengaja.

Alur Penggunaan

Saat aplikasi dijalankan, splash screen ditampilkan selama 3 detik.
Setelah splash screen, pengguna diarahkan ke aplikasi utama untuk memilih film, lokasi, waktu tayang, metode pembayaran, dan melanjutkan ke pemilihan kursi.
Pengguna memilih kursi secara interaktif. Kursi yang dipilih akan ditandai dan disimpan dalam daftar.
Setelah pemilihan selesai, tiket akan ditampilkan dalam pesan pop-up dan disimpan ke dalam file teks.
Jika pengguna ingin membatalkan pesanan, tombol "Cancel Order" dapat digunakan untuk mereset semua pilihan.