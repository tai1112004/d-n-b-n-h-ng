package shopping.Service.imlp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import shopping.Service.CloudinaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryIMPL implements CloudinaryService {
	
	@Autowired
    private Cloudinary cloudinary;

    public String extractPublicId(String imageUrl) {
        try {
            String[] parts = imageUrl.split("/upload/");
            if (parts.length < 2) return null;

            String path = parts[1]; // v1234567890/abc123.png
            String[] pathParts = path.split("/");

            String lastPart = pathParts[pathParts.length - 1]; // abc123.png
            return lastPart.replaceAll("\\.[^.]+$", ""); // abc123
        } catch (Exception e) {
            return null;
        }
    }

	@Override
	public boolean deleteImageByUrl(String imageURL) {
		String publicId = extractPublicId(imageURL);
        if (publicId == null) return false;

        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

	}

}
