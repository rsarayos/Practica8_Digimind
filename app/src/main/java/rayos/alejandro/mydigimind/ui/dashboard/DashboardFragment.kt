package rayos.alejandro.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rayos.alejandro.mydigimind.R
import rayos.alejandro.mydigimind.Recordatorio
import rayos.alejandro.mydigimind.databinding.FragmentDashboardBinding
import rayos.alejandro.mydigimind.ui.home.HomeFragment.Companion.carrito

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, { })

        val btnSetTime: Button = root.findViewById(R.id.btnRegister)
        val txtMensaje: TextView = root.findViewById(R.id.mensaje)
        val btnHora: Button = root.findViewById(R.id.btnTime)
        val txtTiempo: TextView = root.findViewById(R.id.txtTiempo)

        btnHora.setOnClickListener { v ->

            val timePickerDialog = TimePickerDialog(context, { timePicker, hourOfDay, minutes ->
                txtTiempo.setText(hourOfDay.toString().padStart(2, '0') + ":" + minutes.toString().padStart(2, '0'))
            }, 0, 0, false)
            timePickerDialog.show()
        }

        val chbMonday: CheckBox = root.findViewById(R.id.chbMonday)
        val chbTuesday: CheckBox = root.findViewById(R.id.chbTuesday)
        val chbWednesday: CheckBox = root.findViewById(R.id.chbWednesday)
        val chbThursday: CheckBox = root.findViewById(R.id.chbThursday)
        val chbFriday: CheckBox = root.findViewById(R.id.chbFriday)
        val chbSaturday: CheckBox = root.findViewById(R.id.chbSaturday)
        val chbSunday: CheckBox = root.findViewById(R.id.chbSunday)

        btnSetTime.setOnClickListener {
            if (!txtMensaje.text.isNullOrEmpty() && !txtTiempo.text.isNullOrEmpty() && (chbMonday.isChecked || chbTuesday.isChecked || chbWednesday.isChecked || chbThursday.isChecked || chbFriday.isChecked || chbSaturday.isChecked || chbSunday.isChecked)) {

                var dias = ""

                if (chbMonday.isChecked && chbTuesday.isChecked && chbWednesday.isChecked && chbThursday.isChecked && chbFriday.isChecked && chbSaturday.isChecked && chbSunday.isChecked) {
                    dias = "Everyday"
                } else {
                    if (chbMonday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Monday" else ", Monday"
                    }

                    if (chbTuesday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Tuesday" else ", Tuesday"
                    }

                    if (chbWednesday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Wednesday" else ", Wednesday"
                    }

                    if (chbThursday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Thursday" else ", Thursday"
                    }

                    if (chbFriday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Friday" else ", Friday"
                    }

                    if (chbSaturday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Saturday" else ", Saturday"
                    }

                    if (chbSunday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Sunday" else ", Sunday"
                    }
                }

                val recordatorio: Recordatorio = Recordatorio(dias, txtTiempo.text.toString(), txtMensaje.text.toString())

                carrito.agregar(recordatorio)

                txtMensaje.setText("")
                txtTiempo.setText("")

                chbMonday.setChecked(false)
                chbTuesday.setChecked(false)
                chbWednesday.setChecked(false)
                chbThursday.setChecked(false)
                chbFriday.setChecked(false)
                chbSaturday.setChecked(false)
                chbSunday.setChecked(false)

                Toast.makeText(context, "Recordatorio registrado", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "Debes escribir lo que quieres recordar, seleccionar al menos un dia y una hora", Toast.LENGTH_LONG).show()
            }

        }

        return root
    }

}