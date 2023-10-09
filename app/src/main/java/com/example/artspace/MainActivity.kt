/*
Nama  :Reny Dewi Prihatina
NIM   :215410008
KelaS : IF-1
 */
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
/*Class Dibawah ini menggambarkan aktivitas utama dalam aplikasi.
 onCreate mempunyai fungsi mengatur tampilan kontennya.
Dalam hal ini, tema ArtSpaceTheme diterapkan ke seluruh aktivitas,
dan ArtSpaceApp() ditampilkan di dalam Surface dengan ukuran penuh.*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}
/*var currentImage by remember { mutableStateOf(1) }: Variabel yang digunakan untuk melacak gambar yang sedang ditampilkan saat ini.
Ini diinisialisasi dengan nilai 1 dan menggunakan remember untuk menjaga nilai state antara pembaruan tampilan.
when(currentImage) { ... }: Ini adalah sebuah ekspresi when yang digunakan untuk menentukan konten yang harus
ditampilkan berdasarkan nilai currentImage.Bergantung pada nilai currentImage, komponen ArtSpaceImage yang sesuai akan ditampilkan.*/
@Composable
fun ArtSpaceApp(){
    var currentImage by remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize())
    /*Bagian berikutnya adalah serangkaian panggilan ArtSpaceImage, di mana setiap panggilan akan menampilkan gambar dan teks yang sesuai dengan nilai currentImage saat ini.
    Setiap panggilan ArtSpaceImage juga menentukan tindakan yang akan diambil saat tombol "Next" atau "Previous" ditekan, yang mengubah nilai currentImage.
    Jadi, fungsi ArtSpaceApp() ini digunakan untuk mengatur tampilan utama aplikasi Anda dan secara dinamis menampilkan berbagai gambar dan teks berdasarkan nilai currentImage.
    Ini memungkinkan navigasi antara berbagai konten gambar dengan mengklik tombol "Next" dan "Previous".*/
    {
        when(currentImage){
            1 -> {
                ArtSpaceImage(
                    imageResource = R.drawable.conversione_de_saulo,
                    nameResource = R.string.L1,
                    authorResource = R.string.Creator,
                    onNextButtonClick = { currentImage = 2 },
                    onPreviousButtonClick = { currentImage = 4 })
            }
            2 -> {
                ArtSpaceImage(
                    imageResource = R.drawable.entombment,
                    nameResource = R.string.L2,
                    authorResource = R.string.Creator,
                    onNextButtonClick = { currentImage = 3 },
                    onPreviousButtonClick = { currentImage = 1 })
            }
            3 -> {
                ArtSpaceImage(
                    imageResource = R.drawable.crocifissione_di_san_pietro,
                    nameResource = R.string.L3,
                    authorResource = R.string.Creator,
                    onNextButtonClick = { currentImage = 4 },
                    onPreviousButtonClick = { currentImage = 2 })
            }
            4 -> {
                ArtSpaceImage(
                    imageResource = R.drawable.leda_e_il_cigno,
                    nameResource = R.string.L4,
                    authorResource = R.string.Creator,
                    onNextButtonClick = { currentImage = 1},
                    onPreviousButtonClick = { currentImage = 3 })
            }
        }
    }
}
@Composable
fun ArtSpaceImage(
    imageResource: Int,
    nameResource: Int,
    authorResource: Int,
    onNextButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit,
    modifier: Modifier = Modifier
)
        /*Box adalah komponen Box yang mengisi seluruh layar dengan kontennya. Ini adalah kontainer utama untuk semua elemen UI dalam komponen ini.
          Column adalah komponen Column yang digunakan untuk menempatkan elemen-elemen UI secara vertikal dalam layout.
          Elemen-elemen ini termasuk gambar, teks judul, dan teks penulis karya seni.
          Text adalah komponen Text yang digunakan untuk menampilkan teks judul dan teks penulis karya seni. Teks ini diberikan oleh nameResource dan authorResource.
          CustomButton adalah pemanggilan fungsi CustomButton, yang adalah komponen tombol kustom yang digunakan untuk menampilkan tombol "Next" dan "Previous" dengan teks yang sesuai.*/
{
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Surface(
                border = BorderStroke(3.dp, color = Color.LightGray)
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier.padding(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp, 16.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(nameResource),
                        modifier = Modifier
                            .padding(16.dp, 16.dp, 16.dp, 0.dp),
                        fontSize = 22.sp
                    )
                    Text(
                        text = stringResource(authorResource),
                        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                }
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp, 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    onClick = { onPreviousButtonClick() },
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.previous)
                )
                CustomButton(
                    onClick = { onNextButtonClick() },
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.next)
                )
            }
        }
    }
}
/* Composable fun CustomButton adalah komponen Compose kustom yang digunakan untuk membuat tombol dengan teks yang ditentukan. Komponen ini menerima tiga parameter:
onClick: Sebuah lambda yang akan dieksekusi ketika tombol diklik.
modifier: Sebuah objek Modifier yang memungkinkan Anda mengubah tampilan atau perilaku tombol.
text: Teks yang akan ditampilkan pada tombol.
Dalam komponen ini, kita menggunakan komponen Button bawaan Compose untuk membuat tombol dan kemudian memberikan tindakan (onClick) dan
(modifier) yang diteruskan dari parameter komponen CustomButton. Teks tombol juga diberikan dari parameter text.*/
@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Text(text = text, Modifier.padding(12.dp, 0.dp))
    }
}

/*Preview(showBackground = true) dam Composable fun DefaultPreview() adalah fungsi Compose yang digunakan untuk menampilkan pratinjau UI (preview)
dari aplikasi Anda selama pengembangan. Pratinjau ini akan menampilkan tampilan ArtSpaceApp() yang dibungkus dalam tema ArtSpaceTheme.*/
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
