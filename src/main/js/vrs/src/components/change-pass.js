import React, {useState} from "react";
import axios from "axios";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import {Link, useNavigate} from "react-router-dom";


const ChangePass = () => {
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);
    const navigation = useNavigate();


    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = {
            currentPassword: currentPassword,
            newPassword: newPassword
        }

        try {
            const userId = localStorage.getItem("id");
            await axios.post(`http://localhost:8080/api/auth/change-password/${userId}`, payload);
            navigation("/dashboard");
            setSuccess(true);
        } catch (e) {
            setError(e.response.data.error);
        }
    }

    return (
        <>
            <div className="bg-gradient-to-r from-cyan-600 to-violet-500 w-full h-full absolute"></div>
            <div className="absolute flex items-center justify-center w-full h-full">
                <div className="bg-white shadow-2xl border border-2 rounded-2xl px-10 py-8">
                    <h2 className="text-2xl font-semibold mb-4">Changing Password</h2>
                    <Formik
                        initialValues={{
                            currentPassword: "",
                            newPassword: "",
                        }}
                        validationSchema={Yup.object({
                            currentPassword: Yup.string().required("Current Password is required"),
                            newPassword: Yup.string().required("New Password is required"),
                        })}
                        onSubmit={async (values, { setSubmitting, setErrors, setStatus }) => {
                            const userId = localStorage.getItem("id");
                            const payload = {
                                currentPassword: values.currentPassword,
                                newPassword: values.newPassword,
                            };

                            try {
                                await axios.post(`http://localhost:8080/api/auth/change-password/${userId}`, payload);
                                setStatus({ success: "The password has been changed successfully." });
                            } catch (e) {
                                setStatus({ error: e.response.data.message });
                                setSubmitting(false);
                            }
                        }}
                    >
                        {({ status }) => (
                            <Form>
                                <div className="mb-4">
                                    <label htmlFor="currentPassword" className="block font-medium text-gray-700">Current Password</label>
                                    <Field
                                        type="password"
                                        id="currentPassword"
                                        name="currentPassword"
                                        className="p-2 border border-gray-300 rounded-xl outline-none w-full"
                                    />
                                    <ErrorMessage name="currentPassword" component="div" className="text-red-500" />
                                </div>
                                <div className="mb-4">
                                    <label htmlFor="newPassword" className="block font-medium text-gray-700">New Password</label>
                                    <Field
                                        type="password"
                                        id="newPassword"
                                        name="newPassword"
                                        className="p-2 border border-gray-300 rounded-xl outline-none w-full"
                                    />
                                    <ErrorMessage name="newPassword" component="div" className="text-red-500" />
                                </div>
                                {status && status.error && <p className="text-red-500">{status.error}</p>}
                                {status && status.success && <p className="text-green-500 mb-4 text-lg">{status.success}</p>}
                                <div className="flex justify-between mt-8">
                                    <button
                                        type="submit"
                                        className="px-4 py-2 bg-indigo-700 text-white rounded-xl outline-0"
                                    >
                                        Change Password
                                    </button>
                                    <Link to="/dashboard">
                                        <button className="px-4 ml-4 py-2 bg-indigo-500 text-white rounded-xl outline-0">Back</button>
                                    </Link>
                                </div>
                            </Form>
                        )}
                    </Formik>
                </div>
            </div>
        </>

    );
}

export default ChangePass;