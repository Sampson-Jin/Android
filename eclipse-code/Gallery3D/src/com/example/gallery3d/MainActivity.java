package com.example.gallery3d;

import com.example.gallery3d.utils.imageUtils;
import com.example.gallery3d.view.CustomGallery;


public class MainActivity extends Activity {
	private CustomGallery mcustomGallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mcustomGallery=(CustomGallery) findViewById(R.id.customGallery);
		int[] imagesID={
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8,
				R.drawable.pic_1,
				R.drawable.pic_2,
				R.drawable.pic_3,
				R.drawable.pic_4,
				R.drawable.pic_5,
				R.drawable.pic_6,
				R.drawable.pic_7,
				R.drawable.pic_8
		};
		
		mcustomGallery.setAdapter(new MyAdapter(imagesID));
		
	}

	class MyAdapter extends BaseAdapter{
		private int[] ivIds;
		 public MyAdapter(int[] ivids) {
			 this.ivIds=ivids;
		}
		@Override
		public int getCount() {
			return ivIds.length;
		}

		@Override
		public Object getItem(int position) {
			return ivIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=null;
			if(convertView!=null){
				imageView=(ImageView) convertView;
			}else {
				imageView=new ImageView(MainActivity.this);
			}
			Bitmap bitmap=imageUtils.getImageBitmap(MainActivity.this.getResources(),(Integer)getItem(position));
			
			BitmapDrawable bitmapDrawable=new BitmapDrawable(bitmap);
			bitmapDrawable.setAntiAlias(true);//寮�鍚姉閿娇
			
			imageView.setImageDrawable(bitmapDrawable);
			//imageView.setImageBitmap(bitmap);
			//imageView.setImageResource((Integer) getItem(position));
			LayoutParams params=new LayoutParams(200,200);
			imageView.setLayoutParams(params);
			return imageView;
		}
		
	}
}
