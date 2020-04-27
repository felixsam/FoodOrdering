package felixsam.github.com.foodordering;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class DialogFragment_Edit_ItemEntry extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_edit_item_fullscreen, container, false);
        final DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        final String item_name = this.getArguments().getString("ITEM_NAME");
        Integer item_price = this.getArguments().getInt("ITEM_PRICE");
        final Integer col_id = this.getArguments().getInt("COL_ID");
        Integer item_quantity = this.getArguments().getInt("QUANTITY");

        Drawable drawable_close = MaterialDrawableBuilder.with(getActivity())
                .setIcon(MaterialDrawableBuilder.IconValue.CLOSE)
                .setColor(Color.WHITE)
                .setToActionbarSize()
                .build();

        ImageButton btn_close = rootView.findViewById(R.id.full_screen_dialog_btn_close);
        btn_close.setImageDrawable(drawable_close);

        TextInputEditText et_item_name = rootView.findViewById(R.id.fullscreen_dialog_edit_item_name);
        final TextInputEditText et_item_quantity = rootView.findViewById(R.id.fullscreen_dialog_edit_item_quantity);
        TextInputEditText et_item_price = rootView.findViewById(R.id.fullscreen_dialog_edit_item_price);

        et_item_name.setText(item_name);
        et_item_price.setText("$" + item_price.toString());
        et_item_quantity.setText(item_quantity.toString());

        (rootView.findViewById(R.id.full_screen_dialog_btn_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        (rootView.findViewById(R.id.full_screen_dialog_btn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delData_items(col_id,item_name);
                dismiss();
                getActivity().recreate();
            }
        });

        (rootView.findViewById(R.id.full_screen_dialog_btn_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer new_quantity = Integer.valueOf(et_item_quantity.getText().toString());
                dbHelper.updateItemQuantity(col_id,new_quantity,item_name);
                dismiss();
                getActivity().recreate();
            }
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}
