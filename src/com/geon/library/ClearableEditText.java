package com.geon.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.geon.library.TextWatcherAdapter.TextWatcherListener;
/**
 * To change clear icon, set
 * 
 * <pre>
 * android:drawableRight="@drawable/custom_icon"
 * </pre>
 */
public class ClearableEditText extends EditText implements OnTouchListener,
		OnFocusChangeListener, TextWatcherListener {

	public interface Listener {
		void didClearText();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private Drawable xD;
	private Listener listener;
	private Activity actividad;

	public ClearableEditText(Context context) {
		super(context);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l = l;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}

	private OnTouchListener l;
	private OnFocusChangeListener f;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD
					.getIntrinsicWidth());
			if (tappedX) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					setText("");
					if (listener != null) {
						listener.didClearText();
					}
				}
				return true;
			}
		}
		if (l != null) {
			return l.onTouch(v, event);
		}
		return false;
	}


	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setClearIconVisible(isNotEmpty(getText()));
		} else {
			setClearIconVisible(false);
			 
		}
		if (f != null) {
			f.onFocusChange(v, hasFocus);
		}
	}
	



	public void onTextChanged(EditText view, String text) {
		if (isFocused()) {
			setClearIconVisible(isNotEmpty(text));
		}
	}
	
	public boolean isNotEmpty(Editable editable){
		if(editable.toString().isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isNotEmpty(String text){
		if(text.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	private void init() {
		xD = getCompoundDrawables()[2];
		if (xD == null) {
			xD = getResources().getDrawable(getDefaultClearIconId());
		}
		xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
		setClearIconVisible(false);
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);

		addTextChangedListener(new TextWatcherAdapter(this, (TextWatcherListener) this));
	}

	private int getDefaultClearIconId() {
		int id = getResources()
				.getIdentifier("ic_clear", "drawable", "android");
		if (id == 0) {
			id = android.R.drawable.presence_offline;
		}
		return id;
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable x = visible ? xD : null;
		if(x==null){
			InputMethodManager imm = (InputMethodManager)getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
		
		}
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
	}
}