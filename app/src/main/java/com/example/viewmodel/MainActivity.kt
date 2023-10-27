package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.ui.theme.ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layar()
                }
            }
        }
    }
}

@Composable
fun TextHasil(namanya: String, telponnya: String, jenisnya: String, alamatnya: String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Telepon : "+ telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        Text(
            text = "Jenis Kelamin :",
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row (
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
        ) {
            options.forEach{ item ->
                Row (
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(
                        item
                    )
                }
            }
        }
    }
}

@Composable
fun SelectStatus(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        Text(
            text = "Status :",
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row (
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
        ) {
            options.forEach{ item ->
                Row (
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(
                        item
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayarForm(cobaViewModel: CobaViewModel = viewModel()) {
    var textNama by remember {
        mutableStateOf("")
    }
    var textTlp by remember {
        mutableStateOf("")
    }
    var textAlm by remember {
        mutableStateOf("")
    }
    var textEmail by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val dataform: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataform = uiState

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Create Your Account",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 7.dp)
        )

        OutlinedTextField(
            value = textNama,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nama Lengkap") },
            onValueChange = {
                textNama = it
            },
        )

        OutlinedTextField(
            value = textTlp,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Telpon") },
            onValueChange = {
                textTlp = it
            },
        )

        OutlinedTextField(
            value = textEmail,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Email") },
            onValueChange = {
                textEmail = it
            },
        )

        SelectJK(
            options = DataSource.jenis.map { id -> context.resources.getString(id) },
            onSelectionChanged = {cobaViewModel.setJenisK(it)}
        )

        SelectStatus(
            options = DataSource.status.map { id -> context.resources.getString(id) },
            onSelectionChanged = {cobaViewModel.setStatus(it)}
        )

        OutlinedTextField(
            value = textAlm,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Alamat") },
            onValueChange = {
                textAlm = it
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                cobaViewModel.insertData(textNama, textTlp, dataform.sex, textAlm, textEmail)
            }
        ) {
            Text(text = "Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextHasil(
            namanya = cobaViewModel.namaUsr,
            telponnya = cobaViewModel.noTlp,
            jenisnya = cobaViewModel.jenisKL,
            alamatnya = cobaViewModel.alamat
        )
    }
}

@Composable
fun Headers(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color.White)
    ) {
        Row (
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_west_24),
                contentDescription = null
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Register",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                )
            }

        }
        Divider()
    }
}

@Composable
fun Layar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Headers()
        LayarForm()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelTheme {
        Layar()
    }
}