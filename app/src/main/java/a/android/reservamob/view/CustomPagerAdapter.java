package a.android.reservamob.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import a.android.reservamob.R;

class CustomPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] resource;

    public CustomPagerAdapter(Context context, int[] resource) {
        this.resource = resource;
        this.context = context;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, null);
        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(resource[position]);
        ViewPager viewPager = (ViewPager) collection;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {

        return resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
